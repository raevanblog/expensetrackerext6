CREATE SCHEMA EXPENSETRACKER;

CREATE TABLE EXPENSETRACKER.USERINFO(
	FIRSTNAME VARCHAR(30) NOT NULL,
	LASTNAME VARCHAR(30) NOT NULL,
	SEX VARCHAR(1) DEFAULT 'M',
	USERNAME VARCHAR(25) PRIMARY KEY,	
	EMAIL VARCHAR(50) NOT NULL,
	ADDRESS VARCHAR(200) NOT NULL,
	MOBILE VARCHAR(10),
	PROFILEPIC CLOB(10M)
);

CREATE TABLE EXPENSETRACKER.ACCOUNTINFO(
    USERNAME VARCHAR(25),
    PASSWORD VARCHAR(25) NOT NULL,
    ISADMIN VARCHAR(1) DEFAULT 'N',
    ISVERIFIED VARCHAR(1)  DEFAULT 'N',
	ISFTLOGIN VARCHAR(1) DEFAULT 'Y',
	ISLOCKED VARCHAR(1) DEFAULT 'N',
	ACTIVATIONKEY VARCHAR(25),
    CURRENCY BIGINT NOT NULL,
	RCVRYEMAIL VARCHAR(50) NOT NULL,
    FOREIGN KEY(USERNAME) REFERENCES EXPENSETRACKER.USERINFO(USERNAME)
);

CREATE TABLE EXPENSETRACKER.CURRENCY (
	ID BIGINT GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
	CURRSYMB VARCHAR(3) NOT NULL,
	CURRTXT VARCHAR(3) NOT NULL
);

CREATE TABLE EXPENSETRACKER.EXPENSE_CATEGORY (
	ID BIGINT GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
	CATEGORY VARCHAR(100) NOT NULL,
	USERNAME VARCHAR(25) NOT NULL,
	DESCRIPTION VARCHAR(100),
	ISEDITABLE VARCHAR(1) DEFAULT 'N',
	FOREIGN KEY(USERNAME) REFERENCES EXPENSETRACKER.USERINFO(USERNAME)		
);

CREATE TABLE EXPENSETRACKER.EXPENSE(
	ID BIGINT GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
	ITEMNAME VARCHAR(100) NOT NULL,
	EXPDATE DATE NOT NULL,
	USERNAME VARCHAR(25) NOT NULL,
	CATEGORY VARCHAR(100) NOT NULL,
	EXPTYPE VARCHAR(25) NOT NULL,
	QTY FLOAT NOT NULL,
	UNIT VARCHAR(6),
	PRICE FLOAT NOT NULL,
	PRICEPERUNIT FLOAT NOT NULL,	
	FOREIGN KEY(USERNAME) REFERENCES EXPENSETRACKER.USERINFO(USERNAME)		
);

CREATE TABLE EXPENSETRACKER.UNITS(
   	ID BIGINT GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
    UNIT VARCHAR(6) NOT NULL,
    DESCRIPTION VARCHAR(50)    
);


CREATE TABLE EXPENSETRACKER.EXPENSETYPE(
	ID BIGINT GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
	EXPTYPE VARCHAR(25) NOT NULL,
	DESCRIPTION VARCHAR(50)
);

CREATE TABLE EXPENSETRACKER.INCOME (
	ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1),
	USERNAME VARCHAR(25) NOT NULL,
	MTH INTEGER NOT NULL,
	YR INTEGER NOT NULL,
	INCOMETYPE VARCHAR(25) NOT NULL,
	INCOME DOUBLE DEFAULT 0.0 NOT NULL,
	PRIMARY KEY(INCOMETYPE, MTH, YR, USERNAME),
	FOREIGN KEY(USERNAME) REFERENCES EXPENSETRACKER.USERINFO(USERNAME)
);

CREATE TABLE EXPENSETRACKER.INVENTORY (
	ID BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
	ITEMNAME VARCHAR(100) NOT NULL,
	CATEGORY VARCHAR(100) NOT NULL,
	QTY FLOAT NOT NULL,
	UNIT VARCHAR(6),
	USERNAME VARCHAR(25) NOT NULL,	
	FOREIGN KEY(USERNAME) REFERENCES EXPENSETRACKER.USERINFO(USERNAME)
);

CREATE TABLE EXPENSETRACKER.INCOMETYPE(
	ID BIGINT GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
	INCOMETYPE VARCHAR(25) NOT NULL,
	DESCRIPTION VARCHAR(50)
);
