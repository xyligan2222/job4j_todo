CREATE TABLE users (
                      id SERIAL PRIMARY KEY,
                      name TEXT NOT NULL ,
                      email TEXT NOT NULL ,
                      password TEXT NOT NULL

);

CREATE TABLE item (
                       id SERIAL PRIMARY KEY,
                       description TEXT NOT NULL ,
                       created TIMESTAMP NOT NULL ,
                       done BOOL,
                       id_user int not null references users (id)
);

CREATE TABLE category (
                      id SERIAL PRIMARY KEY,
                      name TEXT NOT NULL

);

DELETE from users where id > 3;
DELETE from item where id > 1;
DELETE from baseOfVacancy where id > 0;
DELETE from vacancy where id > 0;

DELETE from candidate where id > 0;
DELETE from item_category where item_category.item_id> 0;




CREATE TABLE mark (
                      id SERIAL PRIMARY KEY,
                      name TEXT NOT NULL
);

CREATE TABLE model (
                       id SERIAL PRIMARY KEY,
                       name TEXT NOT NULL

);

CREATE TABLE authors (
                       id SERIAL PRIMARY KEY,
                       name TEXT NOT NULL

);


CREATE TABLE books(
                      id SERIAL PRIMARY KEY,
                      name TEXT NOT NULL
);

INSERT INTO category (name) values ('Долгосрочные');
INSERT INTO category (name) values ('Краткосрочные');
INSERT INTO category (name) values ('Высокая срочность');
INSERT INTO category (name) values ('Не срочные');

CREATE TABLE candidate (
                          id SERIAL PRIMARY KEY,
                          name TEXT NOT NULL,
                          experience int NOT NULL,
                          salary float NOT NULL
);

CREATE TABLE vacancy (
                           id SERIAL PRIMARY KEY,
                           name TEXT NOT NULL

);

CREATE TABLE baseOfVacancy (
                         id SERIAL PRIMARY KEY,
                         name TEXT NOT NULL

);