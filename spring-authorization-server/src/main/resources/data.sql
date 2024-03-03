drop table if exists auth.accounts;

create table auth.accounts(
	id int GENERATED ALWAYS AS IDENTITY primary key,
	publicId varchar(50),
	email varchar(20),
	fullName varchar(20),
	role varchar(10),
        active boolean
);