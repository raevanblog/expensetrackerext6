package com.slabs.expense.tracker.common.db.column;

import java.util.Date;

public enum Column {

	PRICEPERUNIT("Unit Price","PRICEPERUNIT", "pricePerUnit",  Double.class), 
	PRICE("Price", "PRICE", "price", Double.class), 
	QTY("Qty","QTY", "qty",  Double.class), 
	EXPTYPE("Expense Type", "EXPTYPE", "exptype", String.class), 
	EXPDATE("Date", "EXPDATE", "expdate", Date.class),
	ITEMNAME("Commodity", "ITEMNAME", "itemName", String.class),
	CATEGORY("Category", "CATEGORY","category", String.class), 
	USERNAME("Username", "USERNAME", "username", String.class), 
	DESCRIPTION("Description", "DESCRIPTION", "description", String.class), 
	CATEGORYID("Id", "CATEGORYID", "categoryId", Integer.class), 
	ID("Id", "ID","id", Integer.class), 
	INCOME("Income", "INCOME", "income",  Double.class), 
	INCOMETYPE("Income Type", "INCOMETYPE", "incometype", String.class), 
	YR("Year", "YR", "yr", Integer.class), 
	MTH("Month", "MTH","mth", Integer.class), 
	ISVERIFIED("Is Verified?", "ISVERIFIED", "isVerified", Boolean.class),
	ISADMIN("Is Admin?", "ISADMIN", "isAdmin", Boolean.class),
	ADDRESS("Address", "ADDRESS", "address", String.class), 
	MOBILE("Mobile", "MOBILE", "mobile", String.class), 
	EMAIL("Email", "EMAIL", "email", String.class), 
	PASSWORD("Password", "PASSWORD", "password", String.class), 
	SEX("Sex", "SEX", "sex", String.class), 
	LASTNAME("Last Name", "LASTNAME", "lastName", String.class), 
	FIRSTNAME("First Name", "FIRSTNAME", "firstName", String.class);

	public String titleName;
	
	public String columnName;

	public String mappingName;
	
	@SuppressWarnings("rawtypes")
	public Class datatype;

	@SuppressWarnings("rawtypes")
	Column(String titleName, String columnName, String mappingName, Class datatype) {
		this.titleName = titleName;
		this.columnName = columnName;
		this.mappingName = mappingName;
		this.datatype = datatype;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getMappingName() {
		return mappingName;
	}

	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

	@Override
	public String toString() {
		return this.name();
	}
	
	

}
