package com.slabs.expensetracker.webservices.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class XmlMapper extends com.fasterxml.jackson.dataformat.xml.XmlMapper {

	private static final long serialVersionUID = 1L;

	public XmlMapper() {
		super();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		this.setDateFormat(dateFormat);
		this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		this.configure(SerializationFeature.INDENT_OUTPUT, true);
	}

}
