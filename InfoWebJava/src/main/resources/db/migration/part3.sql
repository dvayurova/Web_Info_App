--1)
create or replace function fnc_get_readable_transferred_points()
returns table (
    Peer1 varchar,
    Peer2 varchar,
    PointsAmount int
    )
as $$
begin
    return query
        with
        m as (select  tp2.checkingpeer, tp2.checkedpeer, tp2.pointsamount from transferredpoints as tp1, transferredpoints as tp2
           where tp1.checkingpeer = tp2.checkedpeer
           and tp2.checkingpeer = tp1.checkedpeer),
        e as(select tp.checkingpeer, tp.checkedpeer, tp.pointsamount from transferredpoints as tp
            except
            select * from m)
        select * from e
        union
        select m1.checkingpeer, m1.checkedpeer, (m1.pointsamount - m2.pointsamount) as pointsamount from m as m1, m as m2
                where m1.checkingpeer = m2.checkedpeer and m1.checkedpeer = m2.checkingpeer and m1.pointsamount < m2.pointsamount;
end;
$$ language plpgsql;

select * from fnc_get_readable_transferred_points();

--2)
create or replace function fnc_completed_tasks()
returns table (
    Peer varchar,
    Task varchar,
    XP int)
as $$
begin
    return query
    with s as (
    select p2p."Check" from P2P left join verter on p2p."Check" = verter."Check"
    where p2p.State = 'Success' and (verter.state is null or verter.state = 'Success'))
    select c.Peer, c.task, xp.xpamount as "XP"
    from checks as c join xp on c.id = xp."Check" inner join s on s."Check" = c.id;
end
$$ language plpgsql;

select * from fnc_completed_tasks();

--3)
create or replace function fnc_didnt_leave_campus(the_date date)
returns setof varchar
as $$
begin
    return query
    select t.peer from timetracking as t where t.date = the_date and t.peer not in (select tt.peer from timetracking as tt where  tt.date = the_date and tt.state = 2);
end
$$ language plpgsql;

select * from fnc_didnt_leave_campus('2023-01-09');


--4)
create or replace function fnc_peer_points()
returns table (
    Peer varchar,
    PointsChange bigint)
as $$
begin
    return query
    with t as (
        select checkingpeer as peers, pointsamount from transferredpoints
        union all
        select checkedpeer as peers, pointsamount * (-1)from transferredpoints
    )
    select peers, SUM(pointsamount) as PointsChange from t group by peers;
end
$$ language plpgsql;

select * from fnc_peer_points();

--5)
create or replace function fnc_peer_points_readable()
returns table (
    Peer varchar,
    PointsChange bigint)
as $$
begin
    return query
     with t as (
        select peer1 as peer, pointsamount from fnc_get_readable_transferred_points()
        union all
        select peer2 as peer, pointsamount * (-1)from fnc_get_readable_transferred_points()
    )
    select t.peer, SUM(pointsamount) as PointsChange from t group by t.peer;
end
$$ language plpgsql;

select * from fnc_peer_points_readable();

--6)
create or replace function fnc_most_frequent_task_check()
returns table (
    Day date,
    Task varchar)
as $$
begin
    return query
    with t as (
        select checks.date, checks.task, count (*), rank() over (partition by checks.date order by count(*) desc) as rank from checks group by date, checks.task
    )
    select t.date, t.task from t where t.rank = 1;
end
$$ language plpgsql;

select * from fnc_most_frequent_task_check();


--7)
create or replace procedure prc_peer_comp_block(block_name text, RESULT INOUT REFCURSOR)
language plpgsql
as $$
begin
    open RESULT for
        select ch.peer, ch.date as day_
        from checks as ch
            join p2p p on ch.id = p."Check"
            join verter v on ch.id = v."Check"
        where p.state = 'Success'
          AND (v.state = 'Success' OR v.state is NULL)
          AND task = (select max(title)
              from tasks
              where title similar to block_name || '[0-9]%')
        order by day_;
end
$$;

commit;
BEGIN;
CALL prc_peer_comp_block('CPP', 'RESULT');
FETCH ALL IN "RESULT";
END;

--8)
create or replace function fnc_find_max_recomend()
returns table (
            Peer varchar,
            RecommendedPeer varchar)
as $$
begin
    return query
    with all_peer as (select p.nickname, (case when p.nickname = f.peer1 then f.peer2 else f.peer1 end) as friend
           from peers p
           join friends f on p.nickname = f.peer1 or p.nickname = f.peer2),
    rec_peer as (select all_peer.nickname, rec.recommendedpeer, count(rec.recommendedpeer) as cnt
            from all_peer
            join recommendations rec on all_peer.friend = rec.peer
            where all_peer.nickname <> rec.recommendedpeer
            group by all_peer.nickname, rec.recommendedpeer),
    max_rec as (select nickname, max(cnt) as max_cnt
            from rec_peer
            group by nickname)
    select rec_peer.nickname, rec_peer.recommendedpeer
    from rec_peer
        join max_rec on rec_peer.nickname = max_rec.nickname
    where rec_peer.cnt = max_rec.max_cnt
    order by rec_peer.nickname;
end;
$$ language plpgsql;
select * from fnc_find_max_recomend();

--9)
create or replace procedure prc_find_proc_comp(block_name_1 text, block_name_2 text, RESULT INOUT REFCURSOR)
language plpgsql
as $$
begin
    open RESULT for
        with t1 as (select (COUNT(DISTINCT c1.peer) * 100 / (SELECT COUNT(*) FROM peers)) as only_first
            from checks c1
            where c1.task similar to block_name_1 || '[0-9]%'
                    and not exists(select 1 from checks c2
                                      where c1.peer = c2.peer
                                        and task similar to block_name_2 || '[0-9]%')
            ),
            t2 as (select (COUNT(DISTINCT c1.peer) * 100 / (SELECT COUNT(*) FROM peers)) as only_second
                from checks c1
                where c1.task similar to block_name_2 || '[0-9]%'
                    and not exists(select 1 from checks c2
                                      where c1.peer = c2.peer
                                        and task similar to block_name_1 || '[0-9]%')
            ),
            t3 as (select (COUNT(DISTINCT c1.peer) * 100 / (SELECT COUNT(*) FROM peers)) as both_together
                from checks c1
                where c1.task similar to block_name_1 || '[0-9]%'
                    and exists(select 1 from checks c2
                                      where c1.peer = c2.peer
                                        and task similar to block_name_2 || '[0-9]%')
           ),
            t4 as (select (COUNT(DISTINCT c1.peer) * 100 / (SELECT COUNT(*) FROM peers)) as not_alone
                from checks c1
                where not exists(select 1 from checks c2
                                      where c1.peer = c2.peer
                                        and c2.task similar to block_name_1 || '[0-9]%')
                        and not exists(select 1 from checks c2
                                      where c1.peer = c2.peer
                                        and c2.task similar to block_name_2 || '[0-9]%')
           )
        select
            (select * from t1),
            (select * from t2),
            (select * from t3),
            (select * from t4);
end
$$;

commit;
BEGIN;
CALL prc_find_proc_comp('CPP', 'C', 'RESULT');
FETCH ALL IN "RESULT";
END;

--10)
create or replace function fnc_perc_comp_birthday()
returns table (
        SuccessfulChecks int,
        UnsuccessfulChecks int
              )
as $$
begin
    return query
    with t1 as (select checks.id, checks.task, p2.nickname, p2.birthday, p.id, p."Check",
            p.state p_state, v.id, v."Check", v.state v_state, checks.date
            from checks left join p2p p on checks.id = p."Check"
                left join verter v on checks.id = v."Check"
                join peers p2 on checks.peer = p2.nickname
            where (p."Check" = v."Check" or v."Check" is null)
                    and (p.state <> 'Start')
            order by checks.id),
     t2 as (select task, nickname, birthday, p_state, v_state, date
            from t1
            where v_state <> 'Start' or v_state is null),
     t3 as (select (count(distinct nickname) * 100 / (select count(*) from peers))::int
            from t2
            where v_state = 'Success' and v_state = 'Success'
                and "right"(to_char(date, 'MM-DD'), 5) = "right"(to_char(birthday, 'MM-DD'), 5)),
     t4 as (select (count(distinct nickname) * 100 / (select count(*) from peers))::int
            from t2
            where v_state = 'Failure' or v_state = 'Failure'
                and "right"(to_char(date, 'MM-DD'), 5) = "right"(to_char(birthday, 'MM-DD'), 5))
    select
        (select * from t3),
        (select * from t4);
end;
$$ language plpgsql;
select * from fnc_perc_comp_birthday();

--11)
create or replace procedure prc_find_comp_tasks(task_1 text, task_2 text, task_3 text, result inout refcursor)
language plpgsql
as $$
begin
 open result for
        with t1 as (select checks.peer
                    from checks join verter v on checks.id = v."Check"
                    where checks.task = task_1 and v.state = 'Success'),
             t2 as (select checks.peer
                    from checks join verter v on checks.id = v."Check"
                    where checks.task = task_2 and v.state = 'Success'),
             t3 as (select peer
                   from checks left join public.verter v2 on checks.id = v2."Check"
                                    join public.p2p p on checks.id = p."Check"
                    where checks.task = task_3
                                    and (v2.state = 'Failure' or p.state = 'Failure'))
        select *
        from t1
        intersect
        select *
        from t2
        intersect
        select *
        from t3;
end
$$;
commit;
BEGIN;
CALL prc_find_comp_tasks('C8_3DViewer_v1.0', 'C7_SmartCalc_v1.0', 'C6_s21_matrix', 'RESULT');
FETCH ALL IN "RESULT";
END;

--12)
create or replace function fnc_find_prev_tasks(name varchar)
    returns table(
        resTittle varchar,
        resParent varchar
                 )
    language plpgsql
as $$
begin
    return query
    with recursive rec as(
        select title, parenttask
        from tasks
        where title = name
        union all
        select t.title, t.parenttask
        from rec
            join tasks t on rec.parenttask = t.title
        )
    select *
    from rec;
end;
$$;

create or replace function fnc_find_count_prev_tasks()
    returns table(
        Task varchar,
        PrevCount bigint
                 )
    language plpgsql
as $$
begin
    return query
    select title, (select count(*) - 1 from fnc_find_prev_tasks(title))
    from tasks;
end;
    $$;
select * from fnc_find_count_prev_tasks();

--13)
drop function if exists fnc(count_checks int);
create or replace function fnc(count_checks int)
    returns table(Day date, Count_Success int) as $$
declare
    cnt_suc int;
    cur_date date;
    my_record record;
begin
    for cur_date in (
        select distinct c.date
        from checks c
            join xp x on c.id = x."Check"
            join tasks t on c.task = t.title
        where x.xpamount >= t.maxxp * 0.8
        order by 1
    )
    loop
        cnt_suc := 0;
        for my_record in (
            select *
            from checks c
                join verter v on c.id = v."Check"
            where v.state <> 'Start' and
                  c.date = cur_date
        )
            loop
                if my_record.state = 'Failure' then
                    exit;
                else
                    cnt_suc := cnt_suc + 1;
                end if;
            end loop;
        if cnt_suc >= count_checks then
            return query
                select cur_date as Day,
                       cnt_suc as Count_Success;
        end if;
    end loop;
end;
$$ language plpgsql;

create or replace procedure prc_get_lucky_day(cnt int, RESULT inout refcursor) as
$$
begin
    open RESULT for
        select * from fnc(cnt) order by 2 desc;
end;
$$ language plpgsql;

BEGIN;
CALL prc_get_lucky_day(2, 'RESULT');
FETCH ALL IN "RESULT";
END;

-- 14)

create or replace procedure prc_peer_with_maxXp(in c refcursor) as $$
begin
    open c for
        select peer, sum(xpamount) xp from xp join public.checks c on c.id = xp."Check"
        group by peer order by XP desc
        limit 1;
end;
    $$ language plpgsql;

begin;
    call prc_peer_with_maxXp('cursor');
    fetch all from cursor;
end;

--15)

create or replace procedure prc_peer_visits(
    in timeOfVisit time,
    in numOfVisit int,
    in c refcursor
)as $$
    begin
        open c for
        select peer from (
            select peer, count(state) as countOfState from timetracking
                                      where state= 1
                                      and time < timeOfVisit
                                      group by peer
                         ) as step1
        where countOfState >= numOfVisit;
    end;
    $$ language plpgsql;

begin;
call prc_peer_visits('20:00:00', 2, 'my_cursor');
fetch all from "my_cursor";
end;

--16)

create or replace procedure prc_out(
    in days int,
    in numOfVisit int,
    in c refcursor
)as $$
    begin
        open c for
        select peer, countOfVisit from (
            select peer, sum (state)/2 as countOfVisit from timetracking
                where state = 2 and date >= current_date - interval '1 days' * days group by peer) as q1
        where countOfVisit >= numOfVisit;
    end;
    $$ language plpgsql;

begin;
call prc_out(50, 2, 'my_cursor');
fetch all from "my_cursor";
end;


--17)


drop procedure if exists prc_percentOf_entries(c refcursor) cascade;
create or replace procedure prc_percentOf_entries(in c refcursor) as $$
    begin
        open c for
            with months as (select extract(month from generate_series('2023-01-01'::date, '2023-12-01'::date, '1 month')) as month),
            simple_table as (select peer, time, extract (month from date) as births
                                from timetracking join public.peers p on p.nickname = timetracking.peer
                                    where state = 1),
            all_entries as (select month, coalesce(count(peer), 0) as visit from months as q1
                                left join simple_table as q2 on q1.month = q2.births
                            group by month order by month),
            early_entries as (select month, coalesce(count(peer), 0) as early_visit from months as q1
                                left join  simple_table as q2 on q1.month = q2.births where q2.time < '12:00:00'
                            group by month order by month
            ),
            joined_table as ( select q1.month, q1.visit, coalesce(q2.early_visit, 0) as early
                              from all_entries  q1 left join early_entries q2
                            on q1.month = q2.month)
        select  to_char(to_date(joined_table.month::text, 'MM'), 'Month') as Month,
                case  when visit = 0 then 0 else
            round(early * 100 / visit) end as EarlyEntries
             from joined_table;
    end
    $$ language plpgsql;

begin;
call prc_percentOf_entries('my_cursor');
fetch all from "my_cursor";
end;