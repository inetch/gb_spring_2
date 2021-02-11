alter table ord_order add constraint ord_order_user_fk foreign key (user_id) references com_user(id) on delete no action on update no action;
alter table ord_order add constraint ord_order_status_fk foreign key (status_id) references ord_order_status(id) on delete no action on update no action;
