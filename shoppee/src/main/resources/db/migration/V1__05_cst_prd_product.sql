alter table prd_product add constraint prd_product_pk primary key (id);
alter table prd_product add constraint prd_product_title_uk unique (title);