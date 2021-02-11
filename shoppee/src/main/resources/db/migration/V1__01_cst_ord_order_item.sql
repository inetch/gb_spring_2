alter table ord_order_item add constraint ord_order_item_pk primary key (order_id, product_id);
alter table ord_order_item add constraint ord_order_item_order_fk foreign key (order_id) references ord_order(id) on delete no action on update no action;
alter table ord_order_item add constraint ord_order_item_product_fk foreign key (product_id) references prd_product(id) on delete no action on update no action;
