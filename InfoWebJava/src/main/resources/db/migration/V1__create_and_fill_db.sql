drop table if exists Peers,  Tasks, P2P, Verter, Checks, TransferredPoints, Friends, Recommendations, XP, TimeTracking cascade;

drop type if exists check_status cascade;
create type check_status as enum ('Start', 'Success', 'Failure');

create table Peers (
    Nickname varchar not null primary key,
    Birthday date not null
);

INSERT INTO Peers (Nickname, Birthday) VALUES
                                           ('ronweas', '2000-03-01'),
                                           ('dracoma', '2000-06-05'),
                                           ('neville', '2000-07-30'),
                                           ('harrypo', '2000-07-31'),
                                           ('hermion', '2000-09-19');



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

INSERT INTO Tasks (Title, ParentTask, MaxXP) VALUES
                                                 ('C8_3DViewer_v1.0', 'C7_SmartCalc_v1.0', 750),
                                                 ('CPP1_s21_matrixplus', 'C8_3DViewer_v1.0', 300),
                                                 ('CPP2_s21_containers', 'CPP1_s21_matrixplus', 350),
                                                 ('CPP3_SmartCalc_v2.0', 'CPP2_s21_containers', 600),
                                                 ('CPP4_3DViewer_v2.0', 'CPP3_SmartCalc_v2.0', 750),
                                                 ('A1_s21_maze', 'CPP4_3DViewer_v2.0', 300),
                                                 ('A2_s21_simplenavigator_v_1.0', 'A1_s21_maze', 400);


create table Checks(
    ID serial primary key,
    Peer varchar not null,
    Task varchar not null,
    Date date not null,
    foreign key (Peer) references Peers (Nickname),
    foreign key (Task) references Tasks (Title)
);

INSERT INTO Checks (Peer, Task, Date) VALUES
                                          ('ronweas', 'C8_3DViewer_v1.0', '2023-01-10'),
                                          ('hermion', 'CPP1_s21_matrixplus', '2023-02-09'),
                                          ('harrypo', 'C8_3DViewer_v1.0', '2023-01-12'),
                                          ('dracoma', 'A1_s21_maze', '2023-06-05'),
                                          ('neville', 'CPP1_s21_matrixplus', '2023-02-08'),
                                          ('hermion', 'CPP2_s21_containers', '2023-02-08'),
                                          ('ronweas', 'C5_s21_decimal', '2023-03-01'),
                                          ('harrypo', 'CPP1_s21_matrixplus', '2023-01-10'),
                                          ('harrypo', 'CPP2_s21_containers', '2023-01-10'),
                                          ('harrypo', 'CPP3_SmartCalc_v2.0', '2023-10-15'),
                                          ('harrypo', 'CPP4_3DViewer_v2.0', '2023-07-31'),
                                          ('hermion', 'CPP3_SmartCalc_v2.0', '2023-03-18'),
                                          ('hermion', 'CPP4_3DViewer_v2.0', '2023-03-21'),
                                          ('hermion', 'A1_s21_maze', '2023-09-19'),
                                          ('neville', 'CPP2_s21_containers', '2023-10-15'),
                                          ('neville', 'CPP3_SmartCalc_v2.0', '2023-10-15');

create table P2P (
    ID serial primary key,
    check_id int not null,
    CheckingPeer varchar not null,
    State check_status not null,
    Time time not null,
    foreign key (check_id) references Checks (ID),
    foreign key (CheckingPeer) references Peers (Nickname)
);

INSERT INTO P2P (check_id, CheckingPeer, State, Time) VALUES
                                                          (1, 'hermion', 'Start', '15:15'),
                                                          (1, 'hermion', 'Success', '15:42'),
                                                          (2, 'dracoma', 'Start', '08:45'),
                                                          (2, 'dracoma', 'Success', '09:35'),
                                                          (3, 'hermion', 'Start', '08:45'),
                                                          (3, 'hermion', 'Success', '09:08'),
                                                          (4, 'harrypo', 'Start', '10:00'),
                                                          (4, 'harrypo', 'Failure', '10:06'),
                                                          (5, 'ronweas', 'Start', '12:15'),
                                                          (5, 'ronweas', 'Success', '12:25'),
                                                          (6, 'neville', 'Start', '09:45'),
                                                          (6, 'neville', 'Success', '10:20'),
                                                          (7, 'harrypo', 'Start', '06:44'),
                                                          (7, 'harrypo', 'Success', '06:59'),
                                                          (8, 'neville', 'Start', '06:44'),
                                                          (8, 'neville', 'Success', '07:24'),
                                                          (9, 'hermion', 'Start', '10:24'),
                                                          (9, 'hermion', 'Success', '11:04'),
                                                          (10, 'dracoma', 'Start', '11:00'),
                                                          (10, 'dracoma', 'Success', '11:55'),
                                                          (11, 'ronweas', 'Start', '15:42'),
                                                          (11, 'ronweas', 'Success', '16:22'),
                                                          (12, 'dracoma', 'Start', '18:02'),
                                                          (12, 'dracoma', 'Success', '18:42'),
                                                          (13, 'harrypo', 'Start', '15:31'),
                                                          (13, 'harrypo', 'Success', '16:02'),
                                                          (14, 'dracoma', 'Start', '16:02'),
                                                          (14, 'dracoma', 'Success', '17:12'),
                                                          (15, 'hermion', 'Start', '16:30'),
                                                          (15, 'hermion', 'Success', '16:50'),
                                                          (16, 'dracoma', 'Start', '10:00'),
                                                          (16, 'dracoma', 'Success', '11:00');

create table Verter (
    ID serial primary key,
    check_id int not null,
    State check_status not null,
    Time time not null,
    foreign key (check_id) references Checks (ID)
);
INSERT INTO Verter (check_id, State, Time) VALUES
                                               (1, 'Start', '15:42'),
                                               (1, 'Success', '15:43'),
                                               (2, 'Start', '09:35'),
                                               (2, 'Success', '09:36'),
                                               (3, 'Start', '09:08'),
                                               (3, 'Success', '09:09'),
                                               (5, 'Start', '12:25'),
                                               (5, 'Success', '12:35'),
                                               (6, 'Start', '10:21'),
                                               (6, 'Success', '11:05'),
                                               (7, 'Start', '07:00'),
                                               (7, 'Failure', '07:01'),
                                               (8, 'Start', '07:25'),
                                               (8, 'Success', '07:26'),
                                               (9, 'Start', '11:05'),
                                               (9, 'Success', '11:06'),
                                               (10, 'Start', '11:56'),
                                               (10, 'Success', '11:57'),
                                               (11, 'Start', '16:23'),
                                               (11, 'Success', '17:00'),
                                               (12, 'Start', '18:42'),
                                               (12, 'Success', '18:43'),
                                               (13, 'Start', '16:03'),
                                               (13, 'Success', '16:04'),
                                               (14, 'Start', '17:13'),
                                               (14, 'Success', '17:14'),
                                               (15, 'Start', '16:50'),
                                               (15, 'Success', '16:51'),
                                               (16, 'Start', '11:01'),
                                               (16, 'Failure', '11:02');

create table TransferredPoints (
    ID serial primary key,
    CheckingPeer varchar not null,
    CheckedPeer varchar not null,
    PointsAmount int,
    foreign key (CheckingPeer) references Peers (Nickname),
    foreign key (CheckedPeer) references Peers (Nickname),
    CHECK (CheckingPeer != CheckedPeer)
);

INSERT INTO TransferredPoints (CheckingPeer, CheckedPeer, PointsAmount) VALUES
                                                                            ('ronweas', 'hermion', 2),
                                                                            ('hermion', 'harrypo', 2),
                                                                            ('neville', 'dracoma', 1),
                                                                            ('hermion', 'neville', 2),
                                                                            ('hermion', 'ronweas', 1);

create table Friends(
    ID serial primary key,
    Peer1 varchar not null,
    Peer2 varchar not null,
    foreign key (Peer1) references Peers (Nickname),
    foreign key (Peer2) references Peers (Nickname),
    CHECK (Peer1 != Peer2)
);

INSERT INTO Friends (Peer1, Peer2) VALUES
                                       ('ronweas', 'hermion'),
                                       ('ronweas', 'harrypo'),
                                       ('hermion', 'harrypo'),
                                       ('neville', 'hermion'),
                                       ('neville', 'ronweas'),
                                       ('neville', 'harrypo');

create table Recommendations(
    ID serial primary key,
    Peer varchar not null,
    RecommendedPeer varchar not null,
    foreign key (Peer) references Peers (Nickname),
    foreign key (RecommendedPeer) references Peers (Nickname),
    CHECK (Peer != RecommendedPeer)
);
INSERT INTO Recommendations (Peer, RecommendedPeer) VALUES
                                                        ('ronweas', 'hermion'),
                                                        ('ronweas', 'neville'),
                                                        ('hermion', 'harrypo'),
                                                        ('hermion', 'ronweas'),
                                                        ('harrypo', 'hermion'),
                                                        ('harrypo', 'neville'),
                                                        ('neville', 'ronweas');

create table XP(
    ID serial primary key,
    check_id int not null,
    XPAmount int not null,
    foreign key (check_id) references Checks (ID),
    check (XPAmount > 0)
);
INSERT INTO XP (check_id, XPAmount) VALUES
                                        (1, 600),
                                        (2, 300),
                                        (3, 590),
                                        (5, 300),
                                        (6, 350),
                                        (8, 300),
                                        (9, 600),
                                        (10, 500),
                                        (11, 700),
                                        (12, 600),
                                        (13, 750),
                                        (14, 300),
                                        (15, 350);


create table TimeTracking(
    ID serial primary key,
    Peer varchar not null,
    Date date not null,
    Time time not null,
    State int not null,
    foreign key (Peer) references Peers (Nickname)
);
INSERT INTO TimeTracking (Peer, Date, Time, State) VALUES
                                                       ('ronweas', '2023-01-09', '10:15', 1),
                                                       ('ronweas', '2023-01-10', '10:15', 2),
                                                       ('hermion', '2023-01-09', '07:00', 1),
                                                       ('hermion', '2023-01-10', '10:10', 2),
                                                       ('harrypo', '2023-01-09', '08:25', 1),
                                                       ('harrypo', '2023-01-09', '13:25', 2),
                                                       ('dracoma', '2023-02-08', '09:05', 1),
                                                       ('dracoma', '2023-02-08', '12:36', 2),
                                                       ('dracoma', '2023-02-08', '15:09', 1),
                                                       ('dracoma', '2023-02-08', '20:11', 2),
                                                       ('neville', '2023-03-08', '11:15', 1),
                                                       ('neville', '2023-03-08', '14:41', 2),
                                                       ('hermion', '2023-01-08', '07:00', 1),
                                                       ('hermion', '2023-01-08', '19:00', 2),
                                                       ('harrypo', '2023-04-02', '15:08', 1);

CREATE CAST (CHARACTER VARYING as check_status) WITH INOUT AS IMPLICIT;

