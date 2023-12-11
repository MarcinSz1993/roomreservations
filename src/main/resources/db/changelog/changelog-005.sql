-- liquibase formatted sql

--changeset MarcinSz1993:5



ALTER TABLE room
DROP COLUMN has_balcony;

ALTER TABLE room
DROP COLUMN has_air_conditioning;

ALTER TABLE room
DROP COLUMN has_private_bathroom;

ALTER TABLE room
DROP COLUMN has_sauna;

ALTER TABLE room
DROP COLUMN has_hair_dryer;

DELETE FROM room
WHERE id = 1;

DELETE FROM room
WHERE id = 2;

DELETE FROM room
WHERE id = 3;

DELETE FROM room
WHERE id = 4;

DELETE FROM room
WHERE id = 5;

DELETE FROM room
WHERE id = 6;

DELETE FROM room
WHERE id = 7;

DELETE FROM room
WHERE id = 8;

DELETE FROM room
WHERE id = 9;


DELETE FROM room
WHERE id = 10;

DELETE FROM room
WHERE id = 11;

DELETE FROM room
WHERE id = 12;

DELETE FROM room
WHERE id = 13;

DELETE FROM room
WHERE id = 14;

DELETE FROM room
WHERE id = 15;

DELETE FROM room
WHERE id = 16;

DELETE FROM room
WHERE id = 17;

DELETE FROM room
WHERE id = 18;

DELETE FROM room
WHERE id = 19;

DELETE FROM room
WHERE id = 20;

DELETE FROM room
WHERE id = 21;

DELETE FROM room
WHERE id = 22;

DELETE FROM room
WHERE id = 23;

DELETE FROM room
WHERE id = 24;

DELETE FROM room
WHERE id = 25;

DELETE FROM room
WHERE id = 26;

DELETE FROM room
WHERE id = 27;

DELETE FROM room
WHERE id = 28;

DELETE FROM room
WHERE id = 29;

DELETE FROM room
WHERE id = 30;
ALTER TABLE room
ADD COLUMN facilities varchar(255)