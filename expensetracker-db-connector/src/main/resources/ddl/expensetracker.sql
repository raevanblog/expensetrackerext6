CREATE TABLE EXPENSETRACKER.USERINFO(
FIRSTNAME VARCHAR(30) NOT NULL,
LASTNAME VARCHAR(30) NOT NULL,
SEX VARCHAR(1) DEFAULT 'M',
USERNAME VARCHAR(25) PRIMARY KEY,
PASSWORD VARCHAR(25) NOT NULL,
EMAIL VARCHAR(50) NOT NULL,
MOBILE VARCHAR(10),
PROFILEPIC CLOB(10M),
ADDRESS VARCHAR(200),
ISADMIN VARCHAR(1) DEFAULT 'N',
ISVERIFIED VARCHAR(1)  DEFAULT 'N',
ISFTLOGIN VARCHAR(1) DEFAULT 'Y',
ISLOCKED VARCHAR(1) DEFAULT 'N',
ACTIVATIONKEY VARCHAR(25)
);

/*CREATE TABLE EXPENSETRACKER.FEATURE (
USERNAME VARCHAR(25) PRIMARY KEY,
ISADMIN VARCHAR(1) DEFAULT 'N',
ISVERIFIED VARCHAR(1)  DEFAULT 'N',
ISFTLOGIN VARCHAR(1) DEFAULT 'Y',
ISLOCKED VARCHAR(1) DEFAULT 'N',
ACTIVATIONKEY VARCHAR(25),
FOREIGN KEY(USERNAME) REFERENCES EXPENSETRACKER.USERINFO(USERNAME)
);*/

CREATE TABLE EXPENSETRACKER.MESSAGE (
ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
MSGTO VARCHAR(25) NOT NULL,
MSGFROM VARCHAR(25) NOT NULL,
MSGDATE TIMESTAMP NOT NULL,
SUBJECT VARCHAR(200) NOT NULL,
MESSAGE CLOB(10M) NOT NULL,
ISNEW CHAR(1) DEFAULT 'Y',
FOREIGN KEY(MSGTO) REFERENCES EXPENSETRACKER.USERINFO(USERNAME),
FOREIGN KEY(MSGFROM) REFERENCES EXPENSETRACKER.USERINFO(USERNAME)
);

CREATE TABLE EXPENSETRACKER.QUERY (
ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
MSGFROM VARCHAR(60) NOT NULL,
MSGDATE TIMESTAMP NOT NULL,
SUBJECT VARCHAR(100) NOT NULL,
EMAIL VARCHAR(50) NOT NULL,
MESSAGE CLOB(5M) NOT NULL,
ISNEW CHAR(1) DEFAULT 'Y'
);

CREATE TABLE "EXPENSETRACKER"."EXPENSE_CATEGORY" (
		"CATEGORYID" BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
		"CATEGORY" VARCHAR(100) NOT NULL,
		"USERNAME" VARCHAR(25),
		"DESCRIPTION" VARCHAR(100),
		FOREIGN KEY(USERNAME) REFERENCES EXPENSETRACKER.USERINFO(USERNAME)
		
);

CREATE UNIQUE INDEX "EXPENSETRACKER"."SQL160918095235170" ON "EXPENSETRACKER"."EXPENSE_CATEGORY" ("CATEGORYID" ASC);

CREATE TABLE EXPENSETRACKER.EXPENSE(
ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
ITEMNAME VARCHAR(100) NOT NULL,
EXPDATE DATE NOT NULL,
USERNAME VARCHAR(25) REFERENCES EXPENSETRACKER.USERINFO(USERNAME) NOT NULL,
CATEGORY VARCHAR(100) NOT NULL,
EXPTYPE VARCHAR(25) NOT NULL,
QTY FLOAT NOT NULL,
PRICE FLOAT NOT NULL,
PRICEPERUNIT FLOAT NOT NULL,
INVENTORYIND VARCHAR(1) DEFAULT 'N'
);

CREATE TABLE EXPENSETRACKER.EXPENSETYPE(
ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
EXPTYPE VARCHAR(25),
DESCRIPTION VARCHAR(50)
);

CREATE TABLE EXPENSETRACKER.INCOME (
	ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1),
	USERNAME VARCHAR(25) REFERENCES EXPENSETRACKER.USERINFO(USERNAME) NOT NULL,
	MTH INTEGER NOT NULL,
	YR INTEGER NOT NULL,
	INCOMETYPE VARCHAR(25) NOT NULL,
	INCOME DOUBLE DEFAULT 0.0 NOT NULL,
	PRIMARY KEY(INCOMETYPE, MTH, YR)
);

CREATE TABLE EXPENSETRACKER.INVENTORY (
	ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1),
	EXPID BIGINT REFERENCES EXPENSETRACKER.EXPENSE(ID),
	MTH INTEGER NOT NULL,
	YR INTEGER NOT NULL,
	ITEMNAME VARCHAR(100) NOT NULL,
	CATEGORY VARCHAR(100) NOT NULL,
	QTY FLOAT NOT NULL,
	USERNAME VARCHAR(25) REFERENCES EXPENSETRACKER.USERINFO(USERNAME) NOT NULL
)

CREATE TABLE EXPENSETRACKER.INCOMETYPE(
ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
INCOMETYPE VARCHAR(25),
DESCRIPTION VARCHAR(50)
);

DROP TABLE EXPENSETRACKER.INCOME;
DROP TABLE EXPENSETRACKER.INCOMETYPE;
DROP TABLE EXPENSETRACKER.INVENTORY;
DROP TABLE EXPENSETRACKER.EXPENSE;
DROP TABLE EXPENSETRACKER.EXPENSETYPE;
DROP TABLE EXPENSETRACKER.EXPENSE_CATEGORY;
DROP TABLE EXPENSETRACKER.MESSAGE;
DROP TABLE EXPENSETRACKER.QUERY;
DROP TABLE EXPENSETRACKER.USERINFO;