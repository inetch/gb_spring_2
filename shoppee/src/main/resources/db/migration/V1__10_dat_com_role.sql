insert into com_role (id, role_name) values (1, 'ROLE_EMPLOYEE');
insert into com_role (id, role_name) values (2, 'ROLE_MANAGER');
insert into com_role (id, role_name) values (3, 'ROLE_ADMIN');
update com_role set role_name = 'ROLE_USER', is_default = 1 where id = 1;