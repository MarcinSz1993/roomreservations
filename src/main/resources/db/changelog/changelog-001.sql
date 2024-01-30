--liquibase formatted sql

--changeset MarcinSz1993:1

create table guest (
                       id bigint primary key ,
                       name varchar(255),
                       surname varchar(255),
                       date_of_birth timestamp,
                       email_address varchar(255),
                       phone_number varchar(255)
);

create table room (
                      id bigint primary key,
                      room_number varchar(255) unique,
                      price_per_night double precision,
                      capacity int,
                      available boolean,
                      has_hair_dryer boolean,
                      has_sauna boolean,
                      has_private_bathroom boolean,
                      has_air_conditioning boolean,
                      has_balcony boolean
);


create table reservation (
                             id bigint primary key,
                             price double precision,
                             payment_method varchar(255),
                             payment_status varchar(255),
                             start_reservation timestamp without time zone,
                             end_reservation timestamp without time zone,
                             guest_id bigint,
                             room_id bigint
);

alter table reservation
    add constraint guest_id
        foreign key (guest_id) references guest (id),
    add constraint  room_id
foreign key (room_id) references room(id);