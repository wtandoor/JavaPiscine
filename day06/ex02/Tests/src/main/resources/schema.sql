drop table if exists product cascade;

create table if not exists product(
                                      id integer identity primary key,
                                      name varchar(15)  not null unique ,
                                      price integer not null
);