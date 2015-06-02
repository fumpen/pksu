# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin_information (
  id                        integer primary key AUTOINCREMENT,
  user                      varchar(255),
  password                  varchar(255))
;

create table admin_session (
  timenow                   integer primary key AUTOINCREMENT,
  admin_name                varchar(255),
  session_uuid              varchar(255))
;

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

drop table admin_information;

drop table admin_session;

drop table booking;

PRAGMA foreign_keys = ON;

