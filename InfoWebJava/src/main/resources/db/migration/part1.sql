drop table if exists Peers,  Tasks, P2P, Verter, Checks, TransferredPoints, Friends, Recommendations, XP, TimeTracking cascade;

drop type if exists check_status cascade;
create type check_status as enum ('Start', 'Success', 'Failure');

create table Peers (
    Nickname varchar not null primary key,
    Birthday date not null
);

create table Tasks (
    Title varchar not null primary key,
    ParentTask varchar,
    MaxXP int not null
);
insert into tasks values ('C3_SimpleBashUtils', null, 250);
insert into tasks values ('C2_s21_stringplus', 'C3_SimpleBashUtils', 500);
insert into tasks values ('C5_s21_decimal', 'C2_s21_stringplus', 350);
insert into tasks values ('C6_s21_matrix', 'C5_s21_decimal', 200);
insert into tasks values ('C7_SmartCalc_v1.0', 'C6_s21_matrix', 500);

alter table Tasks
add constraint fk_tasks foreign key (ParentTask) references Tasks (Title);


create table Checks(
    ID serial primary key,
    Peer varchar not null,
    Task varchar not null,
    Date date not null,
    foreign key (Peer) references Peers (Nickname),
    foreign key (Task) references Tasks (Title)
);

create table P2P (
    ID serial primary key,
    check_id int not null,
    CheckingPeer varchar not null,
    State check_status not null,
    Time time not null,
    foreign key (check_id) references Checks (ID),
    foreign key (CheckingPeer) references Peers (Nickname)
);

create table Verter (
    ID serial primary key,
    check_id int not null,
    State check_status not null,
    Time time not null,
    foreign key (check_id) references Checks (ID)
);

create table TransferredPoints (
    ID serial primary key,
    CheckingPeer varchar not null,
    CheckedPeer varchar not null,
    PointsAmount int,
    foreign key (CheckingPeer) references Peers (Nickname),
    foreign key (CheckedPeer) references Peers (Nickname),
    CHECK (CheckingPeer != CheckedPeer)
);

create table Friends(
    ID serial primary key,
    Peer1 varchar not null,
    Peer2 varchar not null,
    foreign key (Peer1) references Peers (Nickname),
    foreign key (Peer2) references Peers (Nickname),
    CHECK (Peer1 != Peer2)
);

create table Recommendations(
    ID serial primary key,
    Peer varchar not null,
    RecommendedPeer varchar not null,
    foreign key (Peer) references Peers (Nickname),
    foreign key (RecommendedPeer) references Peers (Nickname),
    CHECK (Peer != RecommendedPeer)
);

create table XP(
    ID serial primary key,
    check_id int not null,
    XPAmount int not null,
    foreign key (check_id) references Checks (ID),
    check (XPAmount > 0)
);

create table TimeTracking(
    ID serial primary key,
    Peer varchar not null,
    Date date not null,
    Time time not null,
    State int not null,
    foreign key (Peer) references Peers (Nickname)
);

CREATE CAST (CHARACTER VARYING as check_status) WITH INOUT AS IMPLICIT;


CREATE OR REPLACE PROCEDURE import_table_from_csv(p_table_name VARCHAR, p_file_path VARCHAR, p_delimiter CHAR)
LANGUAGE plpgsql AS $$
DECLARE
    column_list TEXT;
BEGIN

    SELECT string_agg(column_name, ',') INTO column_list
    FROM information_schema.columns
    WHERE table_name = p_table_name AND column_name != 'id';


    EXECUTE format('COPY %I (%s) FROM %L WITH DELIMITER %L CSV HEADER', p_table_name, column_list, p_file_path, p_delimiter);
END;
$$;



create or replace procedure export_table_to_csv(table_name varchar, file_path varchar, delimiter char)
language plpgsql as $$
begin
    execute format('copy  %I to %L with delimiter %L csv header', table_name, file_path, delimiter);
end;
$$;

CALL import_table_from_csv('peers', 'classpath:data/peers.csv', ',');
CALL import_table_from_csv('tasks', 'classpath:data/tasks.csv', ',');
CALL import_table_from_csv('checks', 'classpath:data/checks.csv', ',');
CALL import_table_from_csv('p2p', 'classpath:data/p2p.csv', ',');
CALL import_table_from_csv('verter', 'classpath:data/verter.csv', ',');
CALL import_table_from_csv('transferredpoints', 'classpath:data/transferredpoints.csv', ',');
CALL import_table_from_csv('friends', 'classpath:data/friends.csv', ',');
CALL import_table_from_csv('recommendations', 'classpath:data/recommendations.csv', ',');
CALL import_table_from_csv('xp', 'classpath:data/xp.csv', ',');
CALL import_table_from_csv('timetracking', 'classpath:data/time_tracking.csv', ',');






-- CALL export_table_to_csv('peers', '/Users/comedial/SQL2_Info21_v1.0-1/src/csv/export/peers.csv', ',');
-- CALL export_table_to_csv('tasks', '/Users/comedial/SQL2_Info21_v1.0-1/src/csv/export/tasks.csv', ',');
-- CALL export_table_to_csv('checks', '/Users/comedial/SQL2_Info21_v1.0-1/src/csv/export/checks.csv', ',');
-- CALL export_table_to_csv('p2p', '/Users/comedial/SQL2_Info21_v1.0-1/src/csv/export/p2p.csv', ',');
-- CALL export_table_to_csv('verter', '/Users/comedial/SQL2_Info21_v1.0-1/src/csv/export/verter.csv', ',');
-- CALL export_table_to_csv('transferredpoints', '/Users/comedial/SQL2_Info21_v1.0-1/src/csv/export/transferredpoints.csv', ',');
-- CALL export_table_to_csv('friends', '/Users/comedial/SQL2_Info21_v1.0-1/src/csv/export/friends.csv', ',');
-- CALL export_table_to_csv('recommendations', '/Users/comedial/SQL2_Info21_v1.0-1/src/csv/export/recommendations.csv', ',');
-- CALL export_table_to_csv('xp', '/Users/comedial/SQL2_Info21_v1.0-1/src/csv/export/xp.csv', ',');
-- CALL export_table_to_csv('timetracking', '/Users/comedial/SQL2_Info21_v1.0-1/src/csv/export/time_tracking.csv', ',');