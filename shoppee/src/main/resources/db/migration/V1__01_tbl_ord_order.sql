drop table if exists ord_order;
create table ord_order (
    id              bigint          not null auto_increment
  , user_id         bigint          not null
  , status_id       smallint        not null
  , constraint ord_order_pk primary key (id)
) engine=InnoDB default charset=utf8;