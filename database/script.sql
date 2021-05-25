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

DELETE from users where id > 3;
DELETE from item where id > 1;


CREATE TABLE model (
                      id SERIAL PRIMARY KEY,
                      name TEXT NOT NULL

);

CREATE TABLE mark (
                      id SERIAL PRIMARY KEY,
                      name TEXT NOT NULL
);
