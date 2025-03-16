CREATE TABLE reviews(
id  int auto_increment primary key,
order_id int not null,
rating int not null,
comment text not null,
created_at timestamp default current_timestamp,
updated_at timestamp default current_timestamp on update current_timestamp,
foreign key(order_id)  references orders(id) on delete cascade
);