drop table if exists com_currency;
create table com_currency (
    code            char(3)         not null
  , char_code       char(3)         not null
  , currency_symbol char(1)
  , currency_name   varchar(255)    not null
  , exponent        tinyint         not null
) engine=InnoDB default charset=utf8;