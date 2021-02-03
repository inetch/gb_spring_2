alter table com_currency add constraint com_currency_tst_pk primary key (code);
alter table com_currency add constraint com_currency_tst_char_code_uk unique (char_code);