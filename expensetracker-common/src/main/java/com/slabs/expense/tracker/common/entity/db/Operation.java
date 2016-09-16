package com.slabs.expense.tracker.common.entity.db;

public enum Operation {

	SELECT, DELETE, UPDATE, INSERT;

	@Override
	public String toString() {

		return this.name();
	}

}
