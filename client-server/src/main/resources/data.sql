drop table if exists task_domain.tasks;
drop table if exists task_domain.accounts;

create table task_domain.accounts(
	id int GENERATED ALWAYS AS IDENTITY primary key,
	public_id varchar(50),
	full_name varchar(20),
	role varchar(10)
);

INSERT INTO task_domain.accounts (full_name, public_id, "role") VALUES('Aaa Bbb', '286d963b-0862-4798-bbc7-c8288e8747df', 'ADMIN');
INSERT INTO task_domain.accounts (full_name, public_id, "role") VALUES('User Bbb', '432882b3-c183-4c39-92c7-c94b04917114', 'EMPLOYEE');
INSERT INTO task_domain.accounts (full_name, public_id, "role") VALUES('User Ccc', '6e3a0a6b-b1ce-4601-8c09-0cba36506c69', 'EMPLOYEE');
INSERT INTO task_domain.accounts (full_name, public_id, "role") VALUES('User Ddd', '7abf8a42-1b2a-4f9c-a901-e98dcd6987a1', 'EMPLOYEE');
INSERT INTO task_domain.accounts (full_name, public_id, "role") VALUES('Kkk John ', '87ce8b3b-a935-494e-981e-22f737f99edb', 'MANAGER');

create table task_domain.tasks(
	id int GENERATED ALWAYS AS IDENTITY primary key,
	public_id varchar(50),
	description varchar(50),
	status varchar(20),
	assigned_to int
);

INSERT INTO task_domain.tasks (description, status, public_id, assigned_to) VALUES('water plants', 'DONE', 'c93889de-7031-4443-bee3-7b044d173d31', 2);
INSERT INTO task_domain.tasks (description, status, public_id, assigned_to) VALUES('walk a dog', 'DONE', '80de576b-3084-43a5-a76f-a4ead90db0ed', 3);
INSERT INTO task_domain.tasks (description, status, public_id, assigned_to) VALUES('play with a cat', 'INPROGRESS', '4fe28917-0d4b-4149-8d34-1ec0b02ea192', 3);
INSERT INTO task_domain.tasks (description, status, public_id, assigned_to) VALUES('listen music for 10 minutes', 'INPROGRESS', '09cd5388-73e1-400d-9e27-05780d5ed992', 3);
INSERT INTO task_domain.tasks (description, status, public_id, assigned_to) VALUES('maditate', 'INPROGRESS', '7294117c-8b0c-405f-a870-6fa78dc2913b', 4);