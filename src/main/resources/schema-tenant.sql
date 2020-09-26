DROP TABLE IF EXISTS branches;
DROP TABLE IF EXISTS role_privileges;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS privileges;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS screens;
DROP TABLE IF EXISTS modules;

CREATE TABLE branches (
    branch_id      int(11) not null auto_increment,
	name    varchar(255) not null,
	title    varchar(255) not null,
	description    varchar(512) null,
	status 	 varchar(10) not null,
	sales_tax_reg_no 	 varchar(50) null,
	ntn 	 varchar(50) null,
	contact_person_1    varchar(255) not null,
	contact_person_2    varchar(255) null,
	address_line_1    varchar(255) not null,
	address_line_2    varchar(255) null,
	area    varchar(255) not null,
	city    varchar(100) not null,
	country    varchar(100) not null,
	phone    varchar(100) not null,
	mobile_no    varchar(100) not null,
	email varchar(255) null,
	website varchar(255) null,
	head int(1) not null,
    created_date datetime not null,
	created_by varchar(30) not null,
    updated_date datetime,
    updated_by varchar(30) null
);

CREATE TABLE modules (
    module_code    varchar(50) not null,
	module_name    varchar(255) not null
);
CREATE TABLE screens (
    module_code    varchar(50) not null,
	screen_code	   varchar(50) not null,
	screen_name    varchar(255) not null
);

CREATE TABLE privileges (
    privilege_code    varchar(255) not null,
	screen_code    varchar(50) not null,
	name    varchar(255) not null,
    description    varchar(255) not null
);  

CREATE TABLE users (
    username  varchar (50) not null ,
    password   varchar (128) not null ,
    firstname varchar (100) not null ,
    lastname varchar (100) not null ,
    email varchar (100) not null ,
    mobile_no varchar (100) not null,
    enabled     INT(1) not null ,
    ip_address varchar(100) null,
    mac_address varchar(255) null,
    role_code varchar(50) null,
	
    created_date datetime not null,
	created_by varchar(30) not null,
    updated_date datetime,
    updated_by varchar(30) null
);  

CREATE TABLE roles (
    role_code varchar (50) not null ,
    role_name   varchar (128) not null ,
    admin INT(1) NOT NULL DEFAULT 0,
    created_date datetime not null,
	created_by varchar(30) not null,
    updated_date datetime,
    updated_by varchar(30) null
);  

CREATE TABLE role_privileges (
	role_code    varchar(50) not null,
	privilege_code    varchar(255) not null
);  

CREATE TABLE user_roles (
	branch_id 	int(11) not null,
	username    varchar(50) not null,
	role_code    varchar(50) not null
);  

ALTER TABLE branches ADD CONSTRAINT branches_PK PRIMARY KEY (branch_id);

ALTER TABLE modules ADD CONSTRAINT modules_PK PRIMARY KEY (module_code);

ALTER TABLE screens ADD CONSTRAINT screens_PK PRIMARY KEY (module_code, screen_code);
ALTER TABLE screens ADD CONSTRAINT screens_modules_FK FOREIGN KEY (module_code) REFERENCES modules(module_code);

ALTER TABLE privileges ADD CONSTRAINT privileges_PK PRIMARY KEY (privilege_code);
ALTER TABLE privileges ADD CONSTRAINT privileges_screens_FK FOREIGN KEY (screen_code) REFERENCES screens(screen_code);

ALTER TABLE users ADD CONSTRAINT users_PK PRIMARY KEY (username) ;
ALTER TABLE users ADD CONSTRAINT user_roles_FK FOREIGN KEY (role_code) REFERENCES roles(role_code);

ALTER TABLE roles ADD CONSTRAINT roles_PK PRIMARY KEY (role_code) ;

ALTER TABLE role_privileges ADD CONSTRAINT role_privileges_PK PRIMARY KEY (role_code, privilege_code);
ALTER TABLE role_privileges ADD CONSTRAINT role_privileges_role_code_FK FOREIGN KEY (role_code) REFERENCES roles(role_code);
ALTER TABLE role_privileges ADD CONSTRAINT role_privileges_privilege_code_FK FOREIGN KEY (privilege_code) REFERENCES privileges(privilege_code);

ALTER TABLE user_roles ADD CONSTRAINT user_roles_PK PRIMARY KEY (username, branch_id, role_code);
ALTER TABLE user_roles ADD CONSTRAINT user_roles_branch_FK FOREIGN KEY (branch_id) REFERENCES branches(branch_id);
ALTER TABLE user_roles ADD CONSTRAINT user_roles_role_code_FK FOREIGN KEY (role_code) REFERENCES roles(role_code);
ALTER TABLE user_roles ADD CONSTRAINT user_roles_username_FK FOREIGN KEY (username) REFERENCES users(username);

DROP TABLE IF EXISTS accounts;
DROP SEQUENCE IF EXISTS accounts_sequence;

CREATE SEQUENCE accounts_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS accounts(
	id int(11) NOT NULL,
	type INT NOT NULL,
	parent_code BIGINT NULL DEFAULT NULL,
	system_account int(1) NULL,
	level INT NOT NULL,
	code BIGINT NOT NULL,
	name VARCHAR(50) NOT NULL,
	title VARCHAR(50) NOT NULL ,
	description VARCHAR(255) NULL DEFAULT NULL,
	balance DECIMAL(15,2) NULL DEFAULT '0.00',
	sequence INT(1) NOT NULL default 1,
	group_account INT(1) NOT NULL DEFAULT 0,
	status INT NOT NULL,
	opening_date date not null,
	created_date datetime not null,
	created_by VARCHAR(50) not null,
    updated_date datetime,
    updated_by int default null

);
ALTER TABLE accounts ADD CONSTRAINT accounts_PK PRIMARY KEY (id);