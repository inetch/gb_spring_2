drop table if exists prd_price_list;
create table prd_price_list (
    id              smallint        not null auto_increment
  , title           varchar(255)    not null
  , currency_code   char(3)         not null
  , is_default      bool            not null default false
) engine=InnoDB auto_increment=1 default charset=utf8;