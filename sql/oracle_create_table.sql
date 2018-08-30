create table ywgl.wq_employee_test(
  id number(11) ,
  last_name varchar(255) DEFAULT NULL,
  gender char(1) DEFAULT NULL,
  email varchar(255) DEFAULT NULL
)

create sequence SEQ_EMPLOYEE_ID
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;