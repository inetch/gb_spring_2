drop table if exists ord_order_item;
create table ord_order_item (
    order_id        bigint          not null
  , product_id      bigint          not null
  , price           double          not null
  , quantity        bigint          not null
  , total_price     double          not null
) engine=InnoDB default charset=utf8;