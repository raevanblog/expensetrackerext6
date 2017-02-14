package com.slabs.expense.tracker.webservices.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * {@link JSONMappingResolver}
 * 
 * @author Shyam Natarajan
 *
 */
public class JSONMappingResolver implements ContextResolver<ObjectMapper> {

	public ObjectMapper getContext(Class<?> arg0) {
		ObjectMapper mapper = new ObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		mapper.setDateFormat(dateFormat);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return mapper;
	}

}
