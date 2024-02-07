drop procedure if exists AddP2PCheck cascade;

create or replace procedure AddP2PCheck(
    in CheckedNickname varchar (255),
    in CheckerNickname varchar(255),
    in TaskName varchar (255),
    in Status check_status,
    in CheckTime time
) language plpgsql
as
$$
begin
    if (Status = 'Start') then
            insert into checks
            values ((select max(checks.id) from checks) + 1,
                    CheckedNickname,
                    TaskName,
                    now());
            insert into p2p
            values ((select max(id) from p2p) + 1,
                    (select max(id) from checks),
                    CheckerNickname,
                    Status,
                    CheckTime
                   );
    else
        insert into p2p values
                    ((select max(id) from p2p) + 1,
                     (select "Check" from p2p join public.checks c on c.id = p2p."Check"
                      where p2p.checkingpeer = CheckerNickname
                      and TaskName = task
                      and c.peer = CheckedNickname
                      and p2p.state = 'Start'),
                        CheckerNickname,
                        Status,
                        CheckTime
                    );
    end if;
end ;
$$;

call AddP2PCheck('ronweas', 'dracoma', 'C6_s21_matrix', 'Start', '12:00:00');
call AddP2PCheck('ronweas', 'dracoma', 'C6_s21_matrix', 'Failure', '12:45:00');
call AddP2PCheck('hermion', 'harrypo', 'CPP4_3DViewer_v2.0', 'Start', '17:00:00');
call AddP2PCheck('hermion', 'harrypo', 'CPP4_3DViewer_v2.0', 'Success', '18:45:00');

drop procedure if exists VerterCheck cascade;
create or replace procedure VerterCheck (
    in CheckedNickname varchar(255),
    in TaskName varchar(255),
    in Status check_status,
    in CheckTime time
) language plpgsql
as
$$
    declare
        CheckId int := (
            select c.id from p2p join public.checks c on c.id = p2p."Check"
                where task = TaskName
                and state = 'Success'
                and c.peer = CheckedNickname
                order by time desc limit 1
            );
    begin
        if (CheckId > 0) then
            if (Status = 'Start'
                and (select count(*) from verter where "Check" = CheckId) = 0 ) then
                insert into verter (id, "Check", state, time)
                    values ( (select max(id) from verter) + 1,
                            CheckId, 'Start', CheckTime);
            else
                if ((select state from verter
                        where "Check" = CheckId) = 'Start'
                        and (select time from verter where "Check" = CheckId) < CheckTime) then
                    insert into verter (id, "Check", state, time)
                        values ((select max(id) from verter) + 1,
                                CheckId, Status, CheckTime
                               );
                end if;
            end if;
        end if;
    end;
$$;

drop function if exists fnc_transfer_points();
 create or replace function fnc_transfer_points()
 returns trigger as $updatePoints$
     declare
         checked_peer varchar(255);
         points int;
    begin
         if (new.state  = 'Start') then
            checked_peer  = (select peer from checks join public.p2p p on checks.id = p."Check"
                         where checks.id = new."Check" );
             points  = ( select pointsamount from transferredpoints
                        where checkedpeer = checked_peer
                        and checkingpeer = new.checkingpeer );
             if (points > 0) then
                 update transferredpoints set pointsamount = points + 1
                 where checkingpeer = new.checkingpeer
                 and checkedpeer = checked_peer;
                else
                    insert into transferredpoints (id, checkingpeer, checkedpeer, pointsamount)
                    values ((select max(id) from transferredpoints) + 1,
                            new.checkingpeer,
                            checked_peer,
                            1);
             end if;
         end if;
         return new;
     end;
     $updatePoints$ language plpgsql;

create or replace trigger updatePoints
    after insert on p2p
    for each row
execute function fnc_transfer_points();

call VerterCheck('hermion', 'CPP4_3DViewer_v2.0', 'Start', '12:00:00');
call VerterCheck('hermion', 'CPP4_3DViewer_v2.0', 'Success', '12:05:00');


call AddP2PCheck('ronweas', 'neville', 'CPP4_3DViewer_v2.0', 'Start', '18:00:00');
call AddP2PCheck('ronweas', 'neville', 'CPP4_3DViewer_v2.0', 'Success', '19:45:00');

create or replace function fnc_check_correct_xp()
    returns trigger as $checkXp$
    declare
        max_xp int;
        status check_status;
    begin
        max_xp = (select maxxp from tasks join public.checks c on tasks.title = c.task
                               where c.id = new."Check");
        if max_xp is null then
            raise exception 'the check is not found';
        end if;
        status = (select state from p2p where "Check" = new."Check" order by time desc limit 1);
        if (status = 'Failure') then
            raise exception 'Project is failed';
        end if;
        if new.xpamount > max_xp then
            raise exception 'Incorrect xp';
        end if;
        return new;
    end;
    $checkXp$ language plpgsql;

create or replace trigger checkXp
    before insert on xp
    for each row
execute function fnc_check_correct_xp();

insert into xp (id, "Check", xpamount)
values ((select max(id) from xp) + 1,
        7,
        150);

insert into xp (id, "Check", xpamount)
values ((select max(id) from xp) + 1,
        8,
        1500);

insert into xp (id, "Check", xpamount)
values ((select max(id) from xp) + 1,
        9,
        600);
