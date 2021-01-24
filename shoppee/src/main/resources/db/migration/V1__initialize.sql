set foreign_key_checks = 0;

drop table if exists com_user;

create table com_user (
    id          bigint          not null auto_increment
  , username    varchar(255)    not null
  , password    varchar(4096)   null
  , first_name  varchar(255)    null
  , last_name   varchar(255)    null
  , email       varchar(255)    null

  , constraint com_user_pk primary key (id)

  , constraint com_user_username_uk unique (username)
) engine=InnoDB auto_increment=2 default charset=utf8;

insert into com_user (id, username, password, first_name, last_name, email)
values
(1, 'alex','$2a$10$CGamJWPYokRss2FX6WcBJOgFIDKyUoCYnrGtepfVQyDSTdngSxrzK','Alex','GeekBrains','alex@gb.com');

drop table if exists com_role;
create table com_role (
    id          smallint        not null auto_increment
  , role_name   varchar(255)    not null

  , constraint com_role_pk primary key (id)
) engine=InnoDB auto_increment=4 default charset=utf8;

insert into com_role (id, role_name) values (1, 'ROLE_EMPLOYEE');
insert into com_role (id, role_name) values (2, 'ROLE_MANAGER');
insert into com_role (id, role_name) values (3, 'ROLE_ADMIN');

drop table if exists com_user_role;
create table com_user_role (
    user_id     bigint          not null
  , role_id     smallint        not null

  , constraint com_user_role_pk primary key (user_id, role_id)

  , constraint com_user_role_user_fk foreign key (user_id) references com_user(id)
    on delete no action on update no action
  , constraint com_user_role_role_fk foreign key (role_id) references com_role(id)
    on delete no action on update no action
) engine=InnoDB default charset=utf8;

drop table if exists com_currency;
create table com_currency (
    code            char(3)         not null
  , char_code       char(3)         not null
  , currency_symbol char(1)
  , currency_name   varchar(255)    not null
  , exponent        tinyint         not null

  , constraint com_currency_pk primary key (code)

  , constraint com_currency_char_code_uk unique (char_code)
) engine=InnoDB default charset=utf8;

insert into com_currency (code, char_code, currency_symbol, currency_name, exponent) values
(
   '643'
 , 'RUB'
 , '₽'
 , 'Рубль'
 , 2
);

insert into com_currency (code, char_code, currency_symbol, currency_name, exponent) values
(
   '840'
 , 'USD'
 , '$'
 , 'Доллар США'
 , 2
);

insert into com_currency (code, char_code, currency_symbol, currency_name, exponent) values
(
   '978'
 , 'EUR'
 , '€'
 , 'Евро'
 , 2
);

drop table if exists prd_price_list;
create table prd_price_list (
    id              smallint        not null auto_increment
  , title           varchar(255)    not null
  , currency_code   char(3)         not null

  , constraint prd_price_list_pk primary key (id)

  , constraint prd_price_list_curr_fk foreign key (currency_code) references com_currency(code)
    on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=utf8;

drop table if exists prd_product;
create table prd_product (
    id              bigint          not null auto_increment
  , title           varchar(255)    not null

  , constraint prd_product_pk primary key (id)

  , constraint prd_product_title_uk unique (title)
) engine=InnoDB auto_increment=1 default charset=utf8;

drop table if exists prd_price_list_item;
create table prd_price_list_item (
    product_id      bigint          not null
  , price_list_id   smallint        not null
  , price           bigint          not null

  , constraint prd_price_list_item_pk primary key (price_list_id, product_id)

  , constraint prd_price_list_item_product_fk foreign key (product_id) references prd_product(id)
    on delete no action on update no action
  , constraint prd_price_list_item_list_fk foreign key (price_list_id) references prd_price_list(id)
    on delete no action on update no action
) engine=InnoDB default charset=utf8;

set foreign_key_checks = 1;