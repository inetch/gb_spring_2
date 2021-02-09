alter table com_user_role add constraint com_user_role_pk primary key (user_id, role_id);
alter table com_user_role add constraint com_user_role_user_fk foreign key (user_id) references com_user(id) on delete no action on update no action;
alter table com_user_role add constraint com_user_role_role_fk foreign key (role_id) references com_role(id) on delete no action on update no action;