--liquibase formatted sql

--changeset MarcinSz1993:1

create table guest
(
    id            bigint not null
        primary key,
    date_of_birth date,
    email_address varchar(255),
    name          varchar(255),
    phone_number  varchar(255),
    surname       varchar(255)
);

alter table guest
    owner to postgres;

-- Tworzenie tabeli room
create table room
(
    id                   bigint not null
        primary key,
    capacity             integer,
    has_air_conditioning boolean,
    has_balcony          boolean,
    has_hair_dryer       boolean,
    has_private_bathroom boolean,
    has_sauna            boolean,
    available            boolean,
    price_per_night      double precision,
    room_number          varchar(255)
);

alter table room
    owner to postgres;

-- Tworzenie tabeli reservation
create table reservation
(
    id                bigint not null
        primary key,
    end_reservation   date,
    payment_method    varchar(255),
    payment_status    varchar(255),
    price             double precision,
    start_reservation date,
    guest_id          bigint
        constraint fk8rduaf1n8es4jf5wagbjhjepj
            references guest,
    room_id           bigint
        constraint fkm8xumi0g23038cw32oiva2ymw
            references room
);

alter table reservation
    owner to postgres;

-- Tworzenie tabeli userentity
create table userentity
(
    user_id       bigint not null
        primary key,
    user_email    varchar(255),
    user_password varchar(255),
    user_role     varchar(255)
);

alter table userentity
    owner to postgres;