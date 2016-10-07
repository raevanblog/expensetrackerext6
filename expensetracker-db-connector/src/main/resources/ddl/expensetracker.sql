CREATE TABLE EXPENSETRACKER.USERINFO(
FIRSTNAME VARCHAR(30) NOT NULL,
LASTNAME VARCHAR(30) NOT NULL,
SEX VARCHAR(1) DEFAULT 'M',
USERNAME VARCHAR(25) PRIMARY KEY,
PASSWORD VARCHAR(25) NOT NULL,
EMAIL VARCHAR(25) NOT NULL,
MOBILE VARCHAR(10),
ADDRESS VARCHAR(200)
);

CREATE TABLE EXPENSETRACKER.USER_PERMISSION(
USERNAME VARCHAR(25) PRIMARY KEY,
ISADMIN BOOLEAN,
ISVERIFIED BOOLEAN,
FOREIGN KEY(USERNAME) REFERENCES USERINFO(USERNAME)
);

CREATE TABLE "EXPENSETRACKER"."EXPENSE_CATEGORY" (
		"CATEGORYID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
		"CATEGORY" VARCHAR(100) NOT NULL,
		"DESCRIPTION" VARCHAR(100)
		
);

CREATE UNIQUE INDEX "EXPENSETRACKER"."SQL160918095235170" ON "EXPENSETRACKER"."EXPENSE_CATEGORY" ("CATEGORYID" ASC);

CREATE TABLE EXPENSETRACKER.EXPENSE(
ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
ITEMNAME VARCHAR(100) NOT NULL,
EXPDATE DATE NOT NULL,
USERNAME VARCHAR(25) REFERENCES USERINFO(USERNAME) NOT NULL,
CATEGORY VARCHAR(100) NOT NULL,
EXPTYPE VARCHAR(25) NOT NULL,
QTY FLOAT NOT NULL,
PRICE FLOAT NOT NULL,
PRICEPERUNIT FLOAT NOT NULL
);

CREATE TABLE EXPENSETYPE(
ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
EXPTYPE VARCHAR(25),
DESCRIPTION VARCHAR(50)
);


DROP TABLE EXPENSE;
DROP TABLE EXPENSETYPE;
DROP TABLE EXPENSE_CATEGORY;
DROP TABLE USER_PERMISSION;
DROP TABLE USERINFO;
