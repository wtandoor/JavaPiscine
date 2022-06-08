drop table if exists users cascade;

create table if not exists users (
  identifier integer,
  email text,
  password text
);