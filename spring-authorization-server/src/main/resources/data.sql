drop table if exists auth.accounts;

create table auth.accounts(
	id int GENERATED ALWAYS AS IDENTITY primary key,
	publicId varchar(50),
	email varchar(20),
	fullName varchar(20),
	role varchar(10),
        active boolean,
	password varchar(20)
);

INSERT INTO auth.accounts (active, id, email, full_name, "password", public_id, "role") VALUES(true, 1, 'aaa@aaa.com', 'Aaa Bbb', 'password', '286d963b-0862-4798-bbc7-c8288e8747df', 'ADMIN');
INSERT INTO auth.accounts (active, id, email, full_name, "password", public_id, "role") VALUES(true, 2, 'user@aaa.com', 'User Bbb', 'password', '432882b3-c183-4c39-92c7-c94b04917114', 'EMPLOYEE');