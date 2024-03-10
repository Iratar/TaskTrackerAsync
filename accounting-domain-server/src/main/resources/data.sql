----------------------------
--- ACCOUNTING DOMAIN SCRIPTS----
----------------------------

drop table if exists accounting_domain.payment;
drop table if exists accounting_domain.billing_cycle;
drop table if exists accounting_domain.transactions;
drop table if exists accounting_domain.tasks;
drop table if exists accounting_domain.accounts;

create table accounting_domain.accounts(
	id serial PRIMARY KEY,
	public_id varchar(50),	
	role varchar(10),
	balance decimal
);

INSERT INTO accounting_domain.accounts (balance, public_id, "role") VALUES(0, '286d963b-0862-4798-bbc7-c8288e8747df', 'ADMIN');
INSERT INTO accounting_domain.accounts (balance, public_id, "role") VALUES(0, '432882b3-c183-4c39-92c7-c94b04917114', 'EMPLOYEE');
INSERT INTO accounting_domain.accounts (balance, public_id, "role") VALUES(1000, '6e3a0a6b-b1ce-4601-8c09-0cba36506c69', 'EMPLOYEE');
INSERT INTO accounting_domain.accounts (balance, public_id, "role") VALUES(-80, '7abf8a42-1b2a-4f9c-a901-e98dcd6987a1', 'EMPLOYEE');
INSERT INTO accounting_domain.accounts (balance, public_id, "role") VALUES(0, '87ce8b3b-a935-494e-981e-22f737f99edb', 'MANAGER');


create table accounting_domain.tasks(
	id serial PRIMARY KEY,
	public_id varchar(50),
	description varchar(50),	
	assigned_to int,
	cost_assign decimal,
	cost_done decimal
);

INSERT INTO accounting_domain.tasks (description, cost_assign, cost_done, public_id, assigned_to) VALUES('water plants', 10, 20, 'c93889de-7031-4443-bee3-7b044d173d31', 2);
INSERT INTO accounting_domain.tasks (description, cost_assign, cost_done, public_id, assigned_to) VALUES('walk a dog', 11, 21, '80de576b-3084-43a5-a76f-a4ead90db0ed', 3);
INSERT INTO accounting_domain.tasks (description, cost_assign, cost_done, public_id, assigned_to) VALUES('play with a cat', 12, 22, '4fe28917-0d4b-4149-8d34-1ec0b02ea192', 3);
INSERT INTO accounting_domain.tasks (description, cost_assign, cost_done, public_id, assigned_to) VALUES('listen music for 10 minutes', 13, 23, '09cd5388-73e1-400d-9e27-05780d5ed992', 3);
INSERT INTO accounting_domain.tasks (description, cost_assign, cost_done, public_id, assigned_to) VALUES('maditate', 14, 24, '7294117c-8b0c-405f-a870-6fa78dc2913b', 4);


create table accounting_domain.transactions(
	id serial PRIMARY KEY,
	account_id int,	
	task_id int,	
	type varchar(10),
	payment_type varchar(10),
	amount decimal
);

create table accounting_domain.billing_cycle(
	id serial PRIMARY KEY,
	account_id int,	
	cycle_date date,
	amount decimal,
	status varchar(10)
);

create table accounting_domain.payment(
	id serial PRIMARY KEY,
	billing_cycle_id int,	
	transaction_id int,	
	status varchar(10)
);