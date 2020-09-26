INSERT INTO modules(module_code, module_name)VALUES('USER_MGMT', 'User Management');

INSERT INTO screens(module_code, screen_code, screen_name)VALUES('USER_MGMT', 'USERS', 'Users');
INSERT INTO screens(module_code, screen_code, screen_name)VALUES('USER_MGMT', 'ROLES', 'Roles');

INSERT INTO privileges(screen_code, privilege_code, name, description)VALUES('USERS', 'user_view', 'View', 'View User');
INSERT INTO privileges(screen_code, privilege_code, name, description)VALUES('USERS', 'user_add', 'Add', 'Add User');
INSERT INTO privileges(screen_code, privilege_code, name, description)VALUES('USERS', 'user_edit', 'Edit', 'Edit User');
INSERT INTO privileges(screen_code, privilege_code, name, description)VALUES('USERS', 'user_delete', 'Delete', 'Delete User');

INSERT INTO privileges(screen_code, privilege_code, name, description)VALUES('ROLES', 'role_view', 'View', 'View Role');
INSERT INTO privileges(screen_code, privilege_code, name, description)VALUES('ROLES', 'role_add', 'Add', 'Add Role');
INSERT INTO privileges(screen_code, privilege_code, name, description)VALUES('ROLES', 'role_edit', 'Edit', 'Edit Role');
INSERT INTO privileges(screen_code, privilege_code, name, description)VALUES('ROLES', 'role_delete', 'Delete', 'Delete Role');


INSERT INTO roles(role_code, role_name, admin, created_date, created_by)VALUES('ROLE_ADMIN', 'Administrator', 1, sysdate, 'admin');

INSERT INTO roles(role_code, role_name, created_date, created_by)VALUES('ROLE_USER', 'Data Entry', sysdate, 'admin');
INSERT INTO role_privileges(role_code, privilege_code)VALUES('ROLE_USER', 'user_view');
INSERT INTO role_privileges(role_code, privilege_code)VALUES('ROLE_USER', 'user_add');
INSERT INTO role_privileges(role_code, privilege_code)VALUES('ROLE_USER', 'user_delete');
INSERT INTO role_privileges(role_code, privilege_code)VALUES('ROLE_USER', 'role_view');
INSERT INTO role_privileges(role_code, privilege_code)VALUES('ROLE_USER', 'role_edit');
INSERT INTO role_privileges(role_code, privilege_code)VALUES('ROLE_USER', 'role_delete');

INSERT INTO users(username, password, firstname, lastname, email, mobile_no, enabled, role_code, created_date, created_by)VALUES('admin', '$2a$10$ntTKXfL8041B88KFd4i..OsDskjv8fc5pjBoxS1phclszWG6BDGt6', 'Web', 'Admin', 'admin@admin.com', '+923222575480' ,1, 'ROLE_ADMIN', sysdate, 'admin');
INSERT INTO users(username, password, firstname, lastname, email, mobile_no, enabled, created_date, created_by)VALUES('user', 'user', 'Web', 'User', 'user@admin.com', '+923222575480', 1, sysdate, 'admin');
INSERT INTO users(username, password, firstname, lastname, email, mobile_no, enabled, created_date, created_by)VALUES('guest', 'guest', 'Web', 'Visitor', 'guest@admin.com', '+923222575480', 1, sysdate, 'admin');
INSERT INTO users(username, password, firstname, lastname, email, mobile_no, enabled, created_date, created_by)VALUES('tenant1', 'tenant1', 'Web', 'Admin Tenant1', 'admin@admin.com', '+923222575480', 1, sysdate, 'admin');

INSERT INTO branches(name, title, status, contact_person_1, address_line_1, area, city, country, phone, mobile_no, email, website, head, created_by, created_date)
VALUES('GL Super Store', 'GL Super Store Main Branch', 'Active', 'Mr. Shoaib', 'P.O Box No. 123', 'Shahrah-e-Faisal', 'Karachi', 'Pakistan', '+923222575480', '+923222575480', 'ajs_soft_pk@yahoo.com', 'http://www.ajsfinancial.pk', 1, 'admin', sysdate);

INSERT INTO branches(name, title, status, contact_person_1, address_line_1, area, city, country, phone, mobile_no, email, website, head, created_by, created_date)
VALUES('AR Super Store', 'AR Super Store Main Branch', 'Active', 'Mr. Ar', 'P.O Box No. 456', 'Shahrah-e-Liaqut', 'Karachi', 'Pakistan', '+923222575480', '+923222575480', 'ajs_soft_pk@yahoo.com', 'http://www.ajsfinancial.pk', 0, 'admin', sysdate);

INSERT INTO user_roles(username, branch_id, role_code) VALUES('admin', 1, 'ROLE_ADMIN');
INSERT INTO user_roles(username, branch_id, role_code) VALUES('admin', 1, 'ROLE_USER');

INSERT INTO user_roles(username, branch_id, role_code) VALUES('user', 1, 'ROLE_USER');
INSERT INTO user_roles(username, branch_id, role_code) VALUES('guest', 1, 'ROLE_USER');

select @assetId:= accounts_sequence.nextval;

INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@assetId, 1, 1, 'Assets', 'Assets', null, 1, 1, 1, 1, 1, sysdate, sysdate, 'admin');

select @currentAssetId := accounts_sequence.nextval;

INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@currentAssetId, 101, 101, 'Current Assets', 'Current Assets', 1, 1, 2, 1, 1, 1, sysdate, sysdate, 'admin');

select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 10101, 10101, 'Cash', 'Cash', 101, 1, 2, 1, 1, 1, sysdate, sysdate, 'admin');

select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 10102, 10102, 'Bank', 'Bank', 101, 1, 2, 2, 1, 1, sysdate, sysdate, 'admin');

select @ccIhId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@ccIhId, 10103, 10103, 'Customer Cheques In Hand', 'Customer Cheques In Hand', 101, 1, 3, 3, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 10103, 10103001, 'Customer Cheques In Hand', 'Customer Cheques In Hand', 10103, 1, 4, 1, 1, 0, sysdate, sysdate, 'admin');

select @cpdId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@cpdId, 10104, 10104, 'Cheque Payment Dues', 'Cheque Payment Dues', 101, 1, 3, 4, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 10104, 10104001, 'Cheque Payment Dues', 'Cheque Payment Dues', 10104, 1, 4, 1, 1, 0, sysdate, sysdate, 'admin');

select @rcvId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@rcvId, 10105, 10105, 'Receivable', 'Receivable', 101, 1, 3, 5, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 10105, 10105001, 'Receivable', 'Receivable', 10105, 1, 4, 1, 1, 0, sysdate, sysdate, 'admin');

select @invId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@invId, 10106, 10106, 'Inventory', 'Inventory', 101, 1, 3, 6, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 10106, 10106001, 'Inventory', 'Inventory', 10106, 1, 4, 1, 1, 0, sysdate, sysdate, 'admin');

select @invclId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@invclId, 10107, 10107, 'Inventory In Clearing', 'Inventory In Clearing', 101, 1, 3, 7, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 10107, 10107001, 'Inventory In Clearing', 'Inventory In Clearing', 10107, 1, 4, 1, 1, 0, sysdate, sysdate, 'admin');

select @purId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@purId, 10108, 10108, 'Purchases', 'Purchases', 101, 1, 3, 8, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 10108, 10108001, 'Purchases', 'Purchases', 10108, 1, 4, 1, 1, 0, sysdate, sysdate, 'admin');

select @purrId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@purrId, 10109, 10109, 'Purchase Return', 'Purchase Return', 101, 1, 3, 9, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 10109, 10109001, 'Purchase Return', 'Purchase Return', 10109, 1, 4, 1, 1, 0, sysdate, sysdate, 'admin');

select @purdId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@purdId, 10110, 10110, 'Purchase Discount', 'Purchase Discount', 101, 1, 3, 10, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 10110, 10110001, 'Purchase Discount', 'Purchase Discount', 10110, 1, 4, 1, 1, 0, sysdate, sysdate, 'admin');

select @otcurId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@otcurId, 10111, 10111, 'Other Current Assets', 'Other Current Assets', 101, 1, 3, 11, 1, 1, sysdate, sysdate, 'admin');

select @fixedAssetId := accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@fixedAssetId, 102, 102, 'Fixed Assets', 'Fixed Assets', 1, 1, 2, 3, 1, 1, sysdate, sysdate, 'admin');
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(accounts_sequence.nextval, 10201, 10201, 'Accumulated Depreciation', 'Accumulated Depreciation', 102, 1, 3, 1, 1, 1, sysdate, sysdate, 'admin');
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(accounts_sequence.nextval, 10202, 10202, 'Other Fixed Assets', 'Other Fixed Assets', 102, 1, 3, 2, 1, 1, sysdate, sysdate, 'admin');

select @liabilitiesId:= accounts_sequence.nextval;

INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@liabilitiesId, 2, 2, 'Liabilities', 'Liabilities', null, 1, 1, 2, 1, 1, sysdate, sysdate, 'admin');
select @currentLiabilitiesId := accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@currentLiabilitiesId, 201, 201, 'Current Liabilities', 'Current Liabilities', 2, 1, 2, 1, 1, 1, sysdate, sysdate, 'admin');

select @payId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@payId, 20101, 20101, 'Payable', 'Payable', 201, 1, 3, 1, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 20101, 2010101, 'Payable', 'Payable', 20101, 1, 4, 1, 1, 0, sysdate, sysdate, 'admin');
select @otcurId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@otcurId, 20102, 20102, 'Other Current Liabilities', 'Other Current Liabilities', 201, 1, 3, 2, 1, 1, sysdate, sysdate, 'admin');

INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(accounts_sequence.nextval, 202, 202, 'Long Term Liabilities', 'Long Term Liabilities', 2, 1, 2, 2, 1, 1, sysdate, sysdate, 'admin');

select @equityId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@equityId, 3, 3, 'Equity', 'Equity', null, 1, 1, 3, 1, 1, sysdate, sysdate, 'admin');
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(accounts_sequence.nextval, 301, 301, 'Equity/Capital', 'Equity/Capital', 3, 1, 2, 1, 1, 1, sysdate, sysdate, 'admin');
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(accounts_sequence.nextval, 302, 302, 'Drawing', 'Drawing', 3, 1, 2, 2, 1, 1, sysdate, sysdate, 'admin');
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(accounts_sequence.nextval, 303, 303, 'Retained Earning', 'Retained Earning', 3, 1, 2, 3, 1, 1, sysdate, sysdate, 'admin');

select @expenseId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@expenseId, 4, 4, 'Expense', 'Expense', null, 1, 1, 4, 1, 1, sysdate, sysdate, 'admin');
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(accounts_sequence.nextval, 401, 401, 'Operating Expenses', 'Operating Expenses', 4, 1, 2, 1, 1, 1, sysdate, sysdate, 'admin');
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(accounts_sequence.nextval, 402, 402, 'Non Operating Expenses', 'Non Operating Expenses', 4, 1, 2, 2, 1, 1, sysdate, sysdate, 'admin');

select @revenueId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@revenueId, 5, 5, 'Revenue', 'Revenue', null, 1, 1, 5, 1, 1, sysdate, sysdate, 'admin');
select @salesId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@salesId, 501, 501, 'Sales', 'Sales', 5, 1, 2, 1, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 501, 50101, 'Other Sales', 'Other Sales', 501, 1, 3, 1, 1, 0, sysdate, sysdate, 'admin');
select @salesrId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@salesrId, 502, 502, 'Sales Return', 'Sales Return', 5, 1, 2, 2, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 502, 50201, 'Other Sales Return', 'Other Sales Return', 502, 1, 3, 1, 1, 0, sysdate, sysdate, 'admin');
select @salesdId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@salesdId, 503, 503, 'Sales Discount', 'Sales Discount', 5, 1, 2, 3, 1, 1, sysdate, sysdate, 'admin');
select @aId:= accounts_sequence.nextval;
INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(@aId, 503, 50301, 'Other Sales Discount', 'Other Sales Discount', 503, 1, 3, 1, 1, 0, sysdate, sysdate, 'admin');

INSERT INTO accounts (id, type, code, name, title, parent_code, system_account, level, sequence, status, group_account, opening_date, created_date, created_by) 
VALUES(accounts_sequence.nextval, 504, 504, 'Income/Revenue', 'Income/Revenue', 5, 1, 2, 2, 1, 1, sysdate, sysdate, 'admin');