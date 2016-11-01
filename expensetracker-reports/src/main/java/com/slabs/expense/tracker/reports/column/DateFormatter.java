package com.slabs.expense.tracker.reports.column;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

public class DateFormatter implements DRIValueFormatter<String, Date> {

	private static final long serialVersionUID = 1L;

	private static String DEFAULT_DATE_FORMAT = "dd-MM-yyyy";

	public DateFormatter() {
	}

	public DateFormatter(String format) {
		DEFAULT_DATE_FORMAT = format;
	}

	@Override
	public String format(Date value, ReportParameters reportParameters) {
		DateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return formatter.format(value);
	}

	@Override
	public Class<String> getValueClass() {

		return String.class;
	}

}