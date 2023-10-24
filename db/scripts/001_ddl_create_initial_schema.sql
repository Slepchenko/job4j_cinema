create table files
(
    id   serial primary key,
    name varchar not null,
    path varchar not null unique
);

create table genres
(
    id   serial primary key,
    name varchar unique not null
);

create table halls
(
    id          serial primary key,
    name        varchar not null,
    row_count   int     not null,
    place_count int     not null,
    description varchar not null
);

create table films
(
    id                  serial primary key,
    name                varchar                    not null,
    description         varchar                    not null,
    "year"              int                        not null,
    genre_id            int references genres (id) not null,
    minimal_age         int                        not null,
    duration_in_minutes int                        not null,
    file_id             int references files (id)  not null
);

create table film_sessions
(
    id         serial primary key,
    film_id    int references films (id) not null,
    halls_id   int references halls (id) not null,
    start_time timestamp                 not null,
    end_time   timestamp                 not null,
    price      int                       not null
);

create table users
(
    id        serial primary key,
    full_name varchar        not null,
    email     varchar unique not null,
    password  varchar        not null
);

create table tickets
(
    id           serial primary key,
    session_id   int references film_sessions (id) not null,
    row_number   int                               not null,
    place_number int                               not null,
    user_id      int                               not null,
    unique (session_id, row_number, place_number)
);

insert into files(name, path) values('film1', '/film1.png');
insert into files(name, path) values('film2', '/film2.png');
insert into files(name, path) values('film3', '/film3.png');

insert into genres(name) values('Боевик');
insert into genres(name) values('Драма');
insert into genres(name) values('Вестерн');
insert into genres(name) values('Комедия');

insert into halls(name, row_count, place_count, description) values('Красный', 3, 12, 'Бюджетные места');
insert into halls(name, row_count, place_count, description) values('Зеленый', 5, 25, 'Бюджетные места');
insert into halls(name, row_count, place_count, description) values('VIP', 2, 4, 'VIP места');

insert into films(name, description, year, genre_id, minimal_age, duration_in_minutes, file_id) values ('film1', 'desc1', 1999, 2, 16, 90, 1);
insert into films(name, description, year, genre_id, minimal_age, duration_in_minutes, file_id) values ('film2', 'desc2', 2007, 1, 12, 120, 2);
insert into films(name, description, year, genre_id, minimal_age, duration_in_minutes, file_id) values ('film3', 'desc3', 2009, 3, 18, 150, 3);

insert into film_sessions(film_id, halls_id, start_time, end_time, price) values (1, 1, '2023-08-01 14:00:00', '2023-08-01 16:00:00', 100);
insert into film_sessions(film_id, halls_id, start_time, end_time, price) values (2, 2, '2023-08-01 12:55:00', '2023-08-01 14:55:00', 85);
insert into film_sessions(film_id, halls_id, start_time, end_time, price) values (3, 3, '2023-08-01 18:00:00', '2023-08-01 21:00:00', 200);