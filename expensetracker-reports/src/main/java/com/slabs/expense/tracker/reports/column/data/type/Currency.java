package com.slabs.expense.tracker.reports.column.data.type;

import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;

/**
 * {@link Currency} is an abstract class extending {@link DoubleType}.
 * 
 * @author Shyam Natarajan
 *
 */
public abstract class Currency extends BigDecimalType {

	private static final long serialVersionUID = 1L;

	public abstract String currencySymbol();
	
	public abstract String currenyName();

}
