drop table if exists com_role;
create table com_role (
    id          smallint        not null auto_increment
  , role_name   varchar(255)    not null
) engine=InnoDB auto_increment=4 default charset=utf8;
