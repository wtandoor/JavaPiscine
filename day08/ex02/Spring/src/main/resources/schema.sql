drop table if exists passwordsByEmail cascade;
drop table if exists users cascade;

create table if not exists users(
    id serial primary key,
    identifier integer not null ,
    email text not null
);

create table if not exists passwordsByEmail(
                                                 id serial primary key ,
                                                 email text not null,
                                                 password text not null
);