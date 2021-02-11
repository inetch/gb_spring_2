drop table if exists ord_order_status;
create table ord_order_status (
    id          smallint        not null auto_increment
  , status_name varchar(255)    not null
  , constraint ord_order_status_pk primary key (id)
) engine=InnoDB auto_increment=4 default charset=utf8;

