CREATE TABLE product (
	id serial primary key,
	name varchar not null,
	characteristics jsonb not null,
	image bytea not null
);