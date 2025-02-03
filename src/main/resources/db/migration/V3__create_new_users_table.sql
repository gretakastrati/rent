create table users(
id int  not null  primary key,
name text not null,
email text not null ,
created_at timestamp default current_timestamp,
updated_at timestamp default current_timestamp on update current_timestamp,
gender text
);