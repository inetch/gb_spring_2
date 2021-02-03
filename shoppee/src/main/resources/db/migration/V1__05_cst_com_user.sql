alter table com_user add constraint com_user_pk primary key (id);
alter table com_user add constraint com_user_username_uk unique (username);