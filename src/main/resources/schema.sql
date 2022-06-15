create table users(
  id int primary key not null,
  name varchar(20),
  login_id varchar(20) unique not null,
  login_pass varchar(255) not null
);