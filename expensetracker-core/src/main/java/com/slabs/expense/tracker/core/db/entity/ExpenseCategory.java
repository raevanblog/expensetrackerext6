package com.slabs.expense.tracker.core.db.entity;

import java.io.Serializable;

public class ExpenseCategory implements Serializable {

	private static final long serialVersionUID = -2045471654145029311L;

	private int category_id;

	private String category;

	private String description;

	public ExpenseCategory() {
		super();
	}

	public ExpenseCategory(int category_id, String category, String description) {
		super();
		this.category_id = category_id;
		this.category = category;
		this.description = description;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + category_id;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpenseCategory other = (ExpenseCategory) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (category_id != other.category_id)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExpenseCategory [category_id=" + category_id + ", category=" + category + ", description=" + description
				+ "]";
	}

}
