drop database if exists part4_db;

create database part4_db;
create schema public1;

--1)
create table tablename_joannnut(
    table_name_id int not null primary key,
    name varchar(255)
);
create table tablename_elodiawy(
    table_name_id int not null primary key,
    name varchar(255)
);
create table tablename_comedial(
    table_name_id int not null primary key,
    name varchar(255)
);

create or replace procedure prc_drop_table() as $$
declare my_record record;
begin
    for my_record in (
        select table_name from information_schema.tables
        where table_schema = 'public1'
            and table_name similar to 'tablename%'
    )
    loop
        EXECUTE format('DROP TABLE IF EXISTS %I CASCADE', my_record.table_name);
    end loop;
end;
$$ language plpgsql;

call prc_drop_table();

--2)
--Функция возращаюшая таблицу
create or replace function table_ret()
returns table(id int, name varchar)
as $$
    begin
        return query
        select *
        from tablename_joannnut;
    end;
$$ language plpgsql;

--Функция возращаюшая множество целых чисел
create or replace function example_function()
returns setof integer
as $$
begin
    return query select generate_series(1, 5);
end;
$$ language plpgsql;

--Функция возращаюшая целое число
create or replace function int_ret_with_parametrs(name_id int) returns int
as $$
begin
    return name_id;
end;
$$ language plpgsql;

--Функция возращаюшая целое число, но не имеет аргументов
create or replace function int_ret_without_parametrs() returns bigint
as $$
    begin
        return 1;
    end;
$$ language plpgsql;

--Основная процедура
create or replace procedure prc_find_count_scalar_fnc(out count_fnc int)
as $$
declare my_rec record;
begin
    count_fnc := 0;
    for my_rec in (
        select proname, proargnames
        from pg_proc
        where format_type(prorettype, NULL) != 'void'
        and proretset = false and prokind = 'f'
        and pronamespace = (select oid from pg_namespace where nspname = 'public1')
        and proargnames is not null
    )
        loop
            count_fnc := count_fnc + 1;
            raise notice 'Function name: %, parameter names: %', my_rec.proname, my_rec.proargnames;
        end loop;
end;
$$ language plpgsql;

call prc_find_count_scalar_fnc(NULL);

--3)
--Создание новой таблицы
drop table if exists table_names;
create table table_names(
    id int primary key, name varchar, surname varchar);

--Создание тригерной функции
create or replace function tr_function_one() returns trigger
as $$
    begin
        raise notice 'Create first trigger';
        return new;
    end;
$$ language plpgsql;

--Создание первого триггера
create trigger my_trigger
after insert on table_names
for each row execute function tr_function_one();

--Создание тригерной функции
create or replace function tr_function_two() returns trigger
as $$
    begin
        raise notice 'Create second trigger';
        return new;
    end;
$$ language plpgsql;

--Создание второго триггера
create trigger my_trigger_2
before delete on table_names
for each row execute function tr_function_two();

insert into table_names
values (1, 'kamil', 'sultanov'),
       (2, 'shamil', 'aliev');

delete from table_names
where name = 'shamil';

--Основная процедура
create or replace procedure prc_drop_triggers(out count_tr int)
as $$
declare my_rec record;
begin
    count_tr := 0;
    for my_rec in (
        select event_object_table, trigger_name
        from information_schema.triggers
    )
    loop
        execute format('DROP TRIGGER IF EXISTS %I ON %I CASCADE', my_rec.trigger_name, my_rec.event_object_table);
        count_tr := count_tr + 1;
    end loop;
end;
$$ language plpgsql;

call prc_drop_triggers(NULL);

--4)
create or replace procedure prc_find_text(search_text text, RESULT inout refcursor) as
$$
    begin
        open RESULT for
        select proname, prosrc
        from pg_proc
        where pronamespace = (select oid from pg_namespace where nspname = 'public1')
            and ((prokind  = 'f'and format_type(prorettype, NULL) != 'void'
            and proretset = false and proargnames is not null) or prokind = 'p')
            and prosrc ILIKE '%' || search_text || '%'
        order by proname;
    end;
$$ language plpgsql;

begin;
call prc_find_text('count', 'RESULT');
fetch all in "RESULT";
end;
