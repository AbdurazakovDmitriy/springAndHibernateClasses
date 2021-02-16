CREATE TABLE product_category (
	product_id int not null,
	category_id int not null,
	primary key (product_id, category_id),
	FOREIGN KEY (product_id) REFERENCES product (id),
	FOREIGN KEY (category_id) REFERENCES category (id)
);