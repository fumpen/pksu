# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table booking (
  id                        varchar(255) not null,
  name                      varchar(255),
  lname                     varchar(255),
  dato                      date,
  constraint pk_booking primary key (id))
;

create sequence booking_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists booking;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists booking_seq;

