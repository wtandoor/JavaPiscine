drop schema if exists public cascade;

create table if not exists users (
  identifier integer,
  email text,
  password text
);