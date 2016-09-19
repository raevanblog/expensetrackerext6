package com.slabs.expense.tracker.core.web.services;

import java.util.Set;

import org.glassfish.jersey.server.ResourceConfig;

import com.slabs.expense.tracker.core.JAXBContextResolver;

public class ExpenseTrackerServices extends ResourceConfig {

	public ExpenseTrackerServices() {
		super();
		packages("com.slabs.expense.tracker.core.web");
		register(JAXBContextResolver.class);
		register(com.slabs.expense.tracker.core.exception.ExpenseTrackerException.class);
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
