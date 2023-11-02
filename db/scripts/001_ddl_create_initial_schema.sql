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

insert into files(name, path) values('film1', '/film1.webp');
insert into files(name, path) values('film2', '/film2.webp');
insert into files(name, path) values('film3', '/film3.webp');
insert into files(name, path) values('film4', '/film4.webp');


insert into genres(name) values('Боевик');
insert into genres(name) values('Драма');
insert into genres(name) values('Вестерн');
insert into genres(name) values('Комедия');

insert into halls(name, row_count, place_count, description) values('Красный', 3, 12, 'Бюджетные места');
insert into halls(name, row_count, place_count, description) values('Зеленый', 5, 25, 'Бюджетные места');
insert into halls(name, row_count, place_count, description) values('VIP', 2, 4, 'VIP места');

insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id) values ('Хищник', 'Американский вертолет был сбит партизанами в Южной Америке, оставшийся в живых экипаж попал в плен. ЦРУ бросает лучшие силы для освобождения американских граждан — элитная группа спецназа во главе с Датчем отправляется в южноамериканские джунгли. Но там немало повидавшим бойцам предстоит встретиться невероятным ужасом.', 1987, 1, 16, 104, 1);
insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id) values ('Остров проклятых', 'Два американских судебных пристава отправляются на один из островов в штате Массачусетс, чтобы расследовать исчезновение пациентки клиники для умалишенных преступников. При проведении расследования им придется столкнуться с паутиной лжи, обрушившимся ураганом и смертельным бунтом обитателей клиники.', 2009, 2, 18, 138, 2);
insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id) values ('Хороший, плохой, злой', 'Разгар Гражданской войны, Дикий Запад. По воле судьбы трое мужчин вынуждены объединить усилия в поисках украденного золота. Но совместная работа — не самое подходящее занятие для таких отъявленных бандитов. Компаньоны вскоре понимают, что в их дерзком и опасном путешествии по разоренной войной стране самое важное — никому не доверять и держать пистолет наготове.', 1966, 3, 16, 178, 3);
insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id) values ('Не грози южному централу, попивая сок у себя в квартале ', 'Молодой афроамериканец по прозвищу Пепельница переезжает в Южный Централ, Лос-Анджелес к своему отцу. Пепельница гордится тем, что они с отцом выглядят ровесниками, это, по его мнению, «очень круто». А ещё, он старше своего папы на два года. Гордится он и своей бабушкой, которая в свои-то преклонные годы покуривает и не стесняется крепких словечек. Вскоре Пепельница встречается со своим кузеном по прозвищу Лок Дог. Этот парень — гангстер, в его личном арсенале не только пистолеты и автомат, но и ядерная боеголовка советского производства — «для самозащиты». Пепельница должен выбрать свой дальнейший путь в жизни: он может быть «просто хорошим чернокожим» или стать «опасным чернокожим», примкнув к банде своего кузена.', 1995, 4, 18, 89, 4);

insert into film_sessions(film_id, halls_id, start_time, end_time, price) values (1, 1, '2023-08-01 14:00:00', '2023-08-01 16:00:00', 100);
insert into film_sessions(film_id, halls_id, start_time, end_time, price) values (2, 2, '2023-08-01 12:55:00', '2023-08-01 14:55:00', 85);
insert into film_sessions(film_id, halls_id, start_time, end_time, price) values (3, 3, '2023-08-01 18:00:00', '2023-08-01 21:00:00', 200);
insert into film_sessions(film_id, halls_id, start_time, end_time, price) values (4, 2, '2023-08-05 10:00:00', '2023-08-05 11:30:00', 50);
insert into film_sessions(film_id, halls_id, start_time, end_time, price) values (3, 3, '2023-08-06 10:55:00', '2023-08-06 12:00:00', 100);
insert into film_sessions(film_id, halls_id, start_time, end_time, price) values (1, 2, '2023-08-06 15:30:00', '2023-08-06 17:00:00', 120);
insert into film_sessions(film_id, halls_id, start_time, end_time, price) values (4, 2, '2023-08-06 19:40:00', '2023-08-06 21:05:00', 200);
