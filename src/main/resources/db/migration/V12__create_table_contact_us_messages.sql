create table contact_us_messages(
id int auto_increment primary key,
name text not null,
email text not null,
subject text not null,
message text not null,
created_at timestamp default current_timestamp,
updated_at timestamp default current_timestamp on update current_timestamp
);