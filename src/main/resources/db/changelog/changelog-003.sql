-- liquibase formatted sql

--changeset MarcinSz1993:3

create table if not exists userentity (
                user_id bigint primary key,
                user_email varchar(255),
                user_password varchar(255),
                user_role varchar(255)
);