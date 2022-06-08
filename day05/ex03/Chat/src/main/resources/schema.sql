drop schema if exists chat cascade;
CREATE SCHEMA IF NOT EXISTS chat;

create table if not exists chat.users (
    id SERIAL PRIMARY KEY,
    login varchar(20) unique not null,
    password varchar(20) not null
);

create table if not exists chat.rooms (
    id serial primary key,
    name varchar(20) not null,
    owner bigint references chat.users(id)
);

create table if not exists chat.messages (
    id serial primary key,
    author bigint references chat.users(id),
    chatroom bigint references chat.rooms(id),
    text text,
    time text
);
