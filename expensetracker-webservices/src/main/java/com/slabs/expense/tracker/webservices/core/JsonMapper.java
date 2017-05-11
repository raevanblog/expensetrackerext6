package com.slabs.expense.tracker.webservices.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * {@link JSONMappingResolver}
 * 
 * @author Shyam Natarajan
 *
 */
@Component
public class JsonMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public JsonMapper() {
		super();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		this.setDateFormat(dateFormat);
		this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		this.configure(SerializationFeature.INDENT_OUTPUT, true);
	}

}