package com.slabs.expense.tracker.web.services;

import java.util.Set;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.slabs.expense.tracker.web.services.core.JAXBContextResolver;
import com.slabs.expense.tracker.web.services.core.ObjectMapperResolver;
import com.slabs.expense.tracker.web.services.exception.WebServiceException;

/**
 * {@link ExpenseTrackerServices} is the resource configuration for Expense
 * Tracker Web Service
 * 
 * @author Shyam Natarajan
 *
 */
public class ExpenseTrackerServices extends ResourceConfig {

	public ExpenseTrackerServices() {
		super();
		packages("com.slabs.expense.tracker.web.services");
		register(JAXBContextResolver.class);
		register(ObjectMapperResolver.class);
		register(JacksonFeature.class);
		register(WebServiceException.class);
	}

	public ExpenseTrackerServices(Class<?>... classes) {
		super(classes);
	}

	public ExpenseTrackerServices(ResourceConfig original) {
		super(original);
	}

	public ExpenseTrackerServices(Set<Class<?>> classes) {
		super(classes);
	}

}
