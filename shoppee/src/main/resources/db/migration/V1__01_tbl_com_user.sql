drop table if exists com_user;
create table com_user (
    id          bigint          not null auto_increment
  , username    varchar(255)    not null
  , password    varchar(4096)   null
  , first_name  varchar(255)    null
  , last_name   varchar(255)    null
  , email       varchar(255)    null
) engine=InnoDB auto_increment=2 default charset=utf8;