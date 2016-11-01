package com.slabs.expense.tracker.reports;

public enum Month {

	JANUARY("January", "Jan", 1),
	FEBRUARY("February", "Feb", 2),
	MARCH("March", "Mar", 3),
	APRIL("April", "Apr", 4),
	MAY("May", "May", 5),
	JUNE("June", "Jun", 6),
	JULY("July", "Jul", 7),
	AUGUST("August", "Aug", 8),
	SEPTEMBER("September", "Sep", 9),
	OCTOBER("October", "Oct", 10),
	NOVEMBER("November", "Nov", 11),
	DECEMBER("December", "Dec", 12);

	private String name;

	private String shortName;

	private int number;

	Month(String name, String shortName, int number) {
		this.name = name;
		this.shortName = shortName;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {		
		return this.getName();
	}
	
	

}
