drop table if exists com_user_role;
create table com_user_role (
    user_id     bigint          not null
  , role_id     smallint        not null
) engine=InnoDB default charset=utf8;