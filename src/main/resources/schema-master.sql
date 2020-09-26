drop table if exists master_tenant;
drop table if exists oauth_client_details;
create table master_tenant(
	id INTEGER PRIMARY KEY AUTO_INCREMENT, 
	name	VARCHAR(255) NOT NULL, 
	db VARCHAR(255) NOT NULL, 
	username VARCHAR(255), 
	password VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS oauth_client_details(client_id VARCHAR(256) PRIMARY KEY, resource_ids VARCHAR(256), client_secret VARCHAR(256), scope VARCHAR(256), authorized_grant_types VARCHAR(256), web_server_redirect_uri VARCHAR(256), authorities VARCHAR(256), access_token_validity INTEGER, refresh_token_validity INTEGER, additional_information VARCHAR(4096),autoapprove VARCHAR(256));