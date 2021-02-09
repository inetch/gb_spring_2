drop table if exists prd_price_list_item;
create table prd_price_list_item (
    product_id      bigint          not null
  , price_list_id   smallint        not null
  , price           bigint          not null
) engine=InnoDB default charset=utf8;