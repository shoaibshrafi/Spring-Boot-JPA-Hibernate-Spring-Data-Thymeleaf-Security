DROP TABLE IF EXISTS privileges;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_privileges;

CREATE TABLE privileges (
    code    varchar(255) not null,
	name    varchar(255) not null,
    description    varchar(255) not null
);  

CREATE TABLE users (
    
    username  varchar (30) not null ,
    password   varchar (30) not null ,
    firstname varchar (100) not null ,
    lastname varchar (100) not null ,
    email varchar (100) not null ,
    enabled     INT(1) not null ,
	
    created_date datetime not null,
	created_by varchar(30) not null,
    updated_date datetime,
    updated_by varchar(30) null
);  

CREATE TABLE user_privileges (
    username    varchar(30) not null,
	privilege_code    varchar(255) not null,
	enabled     INT(1) not null 
);  

ALTER TABLE privileges ADD CONSTRAINT privileges_PK PRIMARY KEY (code);
ALTER TABLE users ADD CONSTRAINT users_PK PRIMARY KEY (username) ;
ALTER TABLE user_privileges ADD CONSTRAINT user_privileges_PK PRIMARY KEY (username, privilege_code);
