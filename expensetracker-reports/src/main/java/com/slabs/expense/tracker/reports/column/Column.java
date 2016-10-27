package com.slabs.expense.tracker.reports.column;

import java.sql.Date;

public enum Column {

	PRICEPERUNIT("PRICEPERUNIT", "pricePerUnit", Double.class), 
	PRICE("PRICE", "price", Double.class), 
	QTY("QTY", "qty", Double.class), 
	EXPTYPE("EXPTYPE", "exptype", String.class), 
	EXPDATE("EXPDATE", "expdate", Date.class),
	ITEMNAME("ITEMNAME", "itemName", String.class),
	CATEGORY("CATEGORY","category", String.class), 
	USERNAME("USERNAME", "username", String.class), 
	DESCRIPTION("DESCRIPTION", "description", String.class), 
	CATEGORYID("CATEGORYID", "categoryId", Integer.class), 
	ID("ID","id", Integer.class), 
	INCOME("INCOME", "income", Double.class), 
	INCOMETYPE("INCOMETYPE", "incometype", String.class), 
	YR("YR", "yr", Integer.class), 
	MTH("MTH","mth", Integer.class), 
	ISVERIFIED("ISVERIFIED", "isVerified", Boolean.class),
	ISADMIN("ISADMIN", "isAdmin", Boolean.class),
	ADDRESS("ADDRESS", "address", String.class), 
	MOBILE("MOBILE", "mobile", String.class), 
	EMAIL("EMAIL", "email", String.class), 
	PASSWORD("PASSWORD", "password", String.class), 
	SEX("SEX", "sex", String.class), 
	LASTNAME("LASTNAME", "lastName", String.class), 
	FIRSTNAME("FIRSTNAME", "firstName", String.class);

	String columnName;

	String mappingName;
	
	Class datatype;

	Column(String columnName, String mappingName, Class datatype) {
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
