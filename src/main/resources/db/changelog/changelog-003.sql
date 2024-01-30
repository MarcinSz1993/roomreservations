-- liquibase formatted sql

--changeset MarcinSz1993:3

create table if not exists users (
                id bigint primary key,
                email varchar(255),
                password varchar(255),
                role varchar(255)
);