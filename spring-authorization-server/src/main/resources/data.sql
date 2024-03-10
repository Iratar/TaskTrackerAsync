----------------------------
--- AUTH DOMAIN SCRIPTS----
----------------------------

drop table if exists auth.accounts;

create table auth.accounts(
	id serial PRIMARY KEY,
	public_id varchar(50),
	full_name varchar(20),
	email varchar(20),
	role varchar(10),
        active boolean,
	password varchar(20)
);

INSERT INTO auth.accounts (active, email, full_name, "password", public_id, "role") VALUES(true, 'admin@aaa.com', 'Aaa Bbb', 'password', '286d963b-0862-4798-bbc7-c8288e8747df', 'ADMIN');
INSERT INTO auth.accounts (active, email, full_name, "password", public_id, "role") VALUES(true, 'user1@aaa.com', 'User Bbb', 'password', '432882b3-c183-4c39-92c7-c94b04917114', 'EMPLOYEE');
INSERT INTO auth.accounts (active, email, full_name, "password", public_id, "role") VALUES(true, 'user2@aaa.com', 'User Ccc', 'password', '6e3a0a6b-b1ce-4601-8c09-0cba36506c69', 'EMPLOYEE');
INSERT INTO auth.accounts (active, email, full_name, "password", public_id, "role") VALUES(true, 'user3@aaa.com', 'User Ddd', 'password', '7abf8a42-1b2a-4f9c-a901-e98dcd6987a1', 'EMPLOYEE');
INSERT INTO auth.accounts (active, email, full_name, "password", public_id, "role") VALUES(true, 'manager@aaa.com', 'Kkk John ', 'password', '87ce8b3b-a935-494e-981e-22f737f99edb', 'MANAGER');

