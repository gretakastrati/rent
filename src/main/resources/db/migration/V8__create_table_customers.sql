create table customers(
id int not null  auto_increment primary key,
personal_number varchar(255) unique,
phone_number text,
created_at timestamp default current_timestamp,
updated_at timestamp default current_timestamp on update current_timestamp
);