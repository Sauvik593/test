-- customer table
CREATE TABLE dbo.customer (
    id BIGINT PRIMARY KEY IDENTITY (1, 1),
    customer_id NVARCHAR (20) NOT NULL,
	mobile_number NVARCHAR (12) NOT NULL,
    email NVARCHAR (100) NOT NULL,
	title NVARCHAR (10) NOT NULL CHECK(title IN ('Mr', 'Ms')), -- This needs to be checked again
	first_name NVARCHAR (100) NOT NULL,
	middle_name NVARCHAR (100) NOT NULL, -- should this be nullable
	last_name NVARCHAR (100) NOT NULL,
	employment_status NVARCHAR (20) NOT NULL CHECK(employment_status IN ('job', 'self_employed')), -- This needs to be checked again
	dob DATE NOT NULL,
	nationality_fk bigint NOT NULL, -- fk to nationality
	source_type nvarchar(30) CHECK(source_type IN ('deposit_application', 'third_party_aggregate')), -- This needs to be checked again
	source_application_id NVARCHAR(20),
	created_time datetime not null default current_timestamp,
	updated_time datetime not null default current_timestamp
);
-- get by customer id

-- trigger for updated timestamp
CREATE TRIGGER tgr_modstamp_customer
ON dbo.customer
AFTER UPDATE AS
  UPDATE dbo.customer
  SET updated_time = GETDATE()
  WHERE id IN (SELECT DISTINCT id FROM Inserted);


-- customer address table
CREATE TABLE dbo.customer_addresses (
    id BIGINT PRIMARY KEY IDENTITY (1, 1),
	customer_id NVARCHAR (20) NOT NULL, -- fk
	address_type NVARCHAR (10) not null CHECK(address_type IN ('previous', 'current')),
	idx INT, -- probably used to display addresses in order
	address_line1 nvarchar(256) not null,
	address_line2 nvarchar(256),
	town nvarchar(256) not null,
	county nvarchar(256) not null,
	post_code nvarchar(256) not null,
	summary nvarchar(512) not null,
	residence_years INT NOT NULL,
	residence_months INT NOT NULL,
	created_time DATETIME NOT null default current_timestamp,
	updated_time DATETIME NOT NULL default current_timestamp
);
-- get by customer id

-- trigger for updated timestamp
CREATE TRIGGER tgr_modstamp_customer_addresses
ON dbo.customer_addresses
AFTER UPDATE AS
  UPDATE dbo.customer_addresses
  SET updated_time = GETDATE()
  WHERE id IN (SELECT DISTINCT id FROM Inserted);


-- deposit application table
CREATE TABLE dbo.deposit_application (
    id BIGINT PRIMARY KEY IDENTITY (1, 1),
	application_id NVARCHAR (20) NOT NULL,
	customer_id NVARCHAR (20) NOT NULL,
	requirements_consent DATETIME NOT NULL,
	account_type NVARCHAR(100) NOT NULL CHECK(account_type IN ('long_term_7_percent', 'short_term_6_percent')),  -- product type
	intended_deposit_in_pence INT NOT NULL,
	sort_code NVARCHAR(8),
	account_number NVARCHAR(10),
	tnc_agreement_consent_ts datetime, -- This needs to be checked
	personal_details_allowed_ts datetime, -- This needs to be checked
	created_time DATETIME NOT NULL default current_timestamp,
	updated_time DATETIME NOT NULL default current_timestamp
);
-- get by customer id
-- get by application id

-- trigger for updated timestamp
CREATE TRIGGER tgr_modstamp_deposit_application
ON dbo.deposit_application
AFTER UPDATE AS
  UPDATE dbo.deposit_application
  SET updated_time = GETDATE()
  WHERE id IN (SELECT DISTINCT id FROM Inserted);


-- nationality table
CREATE TABLE dbo.nationality (
    id bigint primary key identity (1,1),
    country_code NVARCHAR(4) not null,
	country NVARCHAR(50) NOT NULL,
	display_text NVARCHAR (20) NOT NULL,
	created_time DATETIME default current_timestamp,
	updated_time DATETIME default current_timestamp
);

-- trigger for updated timestamp
CREATE TRIGGER tgr_modstamp_nationality
ON dbo.nationality
AFTER UPDATE AS
  UPDATE dbo.nationality
  SET updated_time = GETDATE()
  WHERE country_code IN (SELECT DISTINCT country_code FROM Inserted);


-- security question answers table
CREATE TABLE dbo.security_question_answers (
    id BIGINT PRIMARY KEY IDENTITY (1, 1),
    customer_id NVARCHAR (20) NOT NULL,
	question NVARCHAR (20) NOT NULL CHECK(question IN ('favourite_letter', 'favourite_number')), -- This needs to be checked again
	answer NVARCHAR (100) NOT NULL,
	created_time datetime NOT NULL default current_timestamp,
	updated_time datetime NOT NULL default current_timestamp
);

-- trigger for updated timestamp
CREATE TRIGGER tgr_modstamp_security_question_answers
ON dbo.security_question_answers
AFTER UPDATE AS
  UPDATE dbo.security_question_answers
  SET updated_time = GETDATE()
  WHERE id IN (SELECT DISTINCT id FROM Inserted);
