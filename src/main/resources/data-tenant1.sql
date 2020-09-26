INSERT INTO privileges(code, name, description)VALUES('ADMIN', 'Administrator', 'Administrator');
INSERT INTO privileges(code, name, description)VALUES('USER', 'User', 'User');
INSERT INTO privileges(code, name, description)VALUES('GUEST', 'Guest', 'Guest');

INSERT INTO users(username, password, firstname, lastname, email, enabled, created_date, created_by)VALUES('admin', 'admin', 'Web', 'Admin', 'admin@admin.com', 1, sysdate, 'admin');
INSERT INTO users(username, password, firstname, lastname, email, enabled, created_date, created_by)VALUES('user', 'user', 'Web', 'User', 'user@admin.com', 1, sysdate, 'admin');
INSERT INTO users(username, password, firstname, lastname, email, enabled, created_date, created_by)VALUES('guest', 'guest', 'Web', 'Visitor', 'guest@admin.com', 1, sysdate, 'admin');
INSERT INTO users(username, password, firstname, lastname, email, enabled, created_date, created_by)VALUES('tenant1', 'tenant1', 'Web', 'Admin Tenant1', 'admin@admin.com', 1, sysdate, 'admin');

INSERT INTO user_privileges(username, privilege_code, enabled)VALUES('admin', 'ADMIN', 1);
INSERT INTO user_privileges(username, privilege_code, enabled)VALUES('user', 'USER', 1);
INSERT INTO user_privileges(username, privilege_code, enabled)VALUES('guest', 'GUEST', 1);
INSERT INTO user_privileges(username, privilege_code, enabled)VALUES('tenant1', 'ADMIN', 1);