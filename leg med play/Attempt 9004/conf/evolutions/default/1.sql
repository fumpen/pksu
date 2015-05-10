# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table booking (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255),
  lname                     varchar(255),
  arr_date                  varchar(255),
  dep_date                  varchar(255),
  room                      varchar(255))
;




# --- !Downs

PRAGMA foreign_keys = OFF;

drop table booking;

PRAGMA foreign_keys = ON;

