drop table if exists prd_product;
create table prd_product (
    id              bigint          not null auto_increment
  , title           varchar(255)    not null
) engine=InnoDB auto_increment=1 default charset=utf8;