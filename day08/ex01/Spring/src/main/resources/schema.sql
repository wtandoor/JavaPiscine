drop table if exists users cascade;

create table if not exists users(
    id serial primary key,
    identifier integer not null ,
    email text not null
);