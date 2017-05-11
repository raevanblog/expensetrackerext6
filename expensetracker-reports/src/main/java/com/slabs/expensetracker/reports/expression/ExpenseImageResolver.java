package com.slabs.expensetracker.reports.expression;

import java.io.InputStream;

import com.slabs.expensetracker.common.database.column.Column;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 * {@link ExpenseImageResolver} will resolve image for the expense type
 * {@code Column.EXPTYPE}
 * 
 * @author Shyam Natarajan
 *
 */
public class ExpenseImageResolver extends AbstractSimpleExpression<InputStream> {

	private static final long serialVersionUID = 1L;

	@Override
	public InputStream evaluate(ReportParameters params) {
		String expenseType = (String) params.getValue(Column.EXPTYPE.mappingName);
		if ("CASH".equalsIgnoreCase(expenseType)) {
			return getClass().getResourceAsStream("/images/cash.png");
		} else if ("CREDIT CARD".equalsIgnoreCase(expenseType) || "DEBIT CARD".equalsIgnoreCase(expenseType)) {
			return getClass().getResourceAsStream("/images/card.png");
		} else {
			return getClass().getResourceAsStream("/images/computer.png");
		}
	}

}
