--liquibase formatted sql

--changeset MarcinSz1993:4

INSERT INTO "Reservation".public.userentity(user_id,user_email,user_password,user_role)
VALUES (2,'test@test.pl','$2a$12$rb4hUxw1fyu2i3ILQZM/XuwTl9t/mJFdUnxk8c9BK1p.pBNKclje2','ROLE_ADMIN');

