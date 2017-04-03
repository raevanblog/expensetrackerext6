INSERT INTO EXPENSETRACKER.USERINFO(USERNAME, FIRSTNAME, LASTNAME, SEX, PASSWORD, EMAIL, ADDRESS, MOBILE, PROFILEPIC, ISADMIN, ISVERIFIED, ISFTLOGIN, ISLOCKED, ACTIVATIONKEY)
VALUES ('admin', 'Administrator', ' ', 'M', 'Seiko#1986', 'raevanblog@gmail.com', ' ', '9894362480', null, 'Y', 'Y', 'N', 'N', null);

/* INCOMETYPE  - Start */
INSERT INTO EXPENSETRACKER.INCOMETYPE(INCOMETYPE, DESCRIPTION) VALUES ('Salary', 'Salary');
INSERT INTO EXPENSETRACKER.INCOMETYPE(INCOMETYPE, DESCRIPTION) VALUES ('Business', 'Business');
INSERT INTO EXPENSETRACKER.INCOMETYPE(INCOMETYPE, DESCRIPTION) VALUES ('Gift', 'Gift');
INSERT INTO EXPENSETRACKER.INCOMETYPE(INCOMETYPE, DESCRIPTION) VALUES ('Others', 'Others');
/* INCOMETYPE  - End */


/* EXPENSETYPE - Start */
INSERT INTO EXPENSETRACKER.EXPENSETYPE(EXPTYPE, DESCRIPTION) VALUES ('Cash', 'Cash');
INSERT INTO EXPENSETRACKER.EXPENSETYPE(EXPTYPE, DESCRIPTION) VALUES ('Credit Card', 'Credit Card');
INSERT INTO EXPENSETRACKER.EXPENSETYPE(EXPTYPE, DESCRIPTION) VALUES ('Debit Card', 'Debit Card');
INSERT INTO EXPENSETRACKER.EXPENSETYPE(EXPTYPE, DESCRIPTION) VALUES ('Net Banking', 'Net Banking');
/* EXPENSETYPE - End */

/* CURRENCY - Start */
INSERT INTO EXPENSETRACKER.CURRENCY(CURRSYMB, CURRTXT) VALUES ('$', 'USD');
INSERT INTO EXPENSETRACKER.CURRENCY(CURRSYMB, CURRTXT) VALUES ('₹', 'INR');
INSERT INTO EXPENSETRACKER.CURRENCY(CURRSYMB, CURRTXT) VALUES ('£', 'GBP');
INSERT INTO EXPENSETRACKER.CURRENCY(CURRSYMB, CURRTXT) VALUES ('€', 'EUR');
/* CURRENCY - End */

/* UNITS - Start */

INSERT INTO EXPENSETRACKER.UNITS(UNIT, DESCRIPTION, USERNAME) VALUES ('g','Gram','admin');
INSERT INTO EXPENSETRACKER.UNITS(UNIT, DESCRIPTION, USERNAME) VALUES ('kg','Kilogram','admin');
INSERT INTO EXPENSETRACKER.UNITS(UNIT, DESCRIPTION, USERNAME) VALUES ('ml','Millilitre','admin');
INSERT INTO EXPENSETRACKER.UNITS(UNIT, DESCRIPTION, USERNAME) VALUES ('l','Litre','admin');
INSERT INTO EXPENSETRACKER.UNITS(UNIT, DESCRIPTION, USERNAME) VALUES ('cm','Centimeter','admin');
INSERT INTO EXPENSETRACKER.UNITS(UNIT, DESCRIPTION, USERNAME) VALUES ('i','Inch','admin');
INSERT INTO EXPENSETRACKER.UNITS(UNIT, DESCRIPTION, USERNAME) VALUES ('km','Kilometer','admin');
INSERT INTO EXPENSETRACKER.UNITS(UNIT, DESCRIPTION, USERNAME) VALUES ('qty','Quantity','admin');

/* UNITS - End */