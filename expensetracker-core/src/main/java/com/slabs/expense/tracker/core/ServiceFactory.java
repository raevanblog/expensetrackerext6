package com.slabs.expense.tracker.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.slabs.expense.tracker.core.services.Services;

public class ServiceFactory {

	private static final Logger L = LoggerFactory.getLogger(ServiceFactory.class);

	private static volatile ServiceFactory factory;

	private ApplicationContext context;

	private ServiceFactory() {

	}

	public static ServiceFactory getInstance() {
		if (factory == null) {
			synchronized (ServiceFactory.class) {
				if (factory == null) {
					factory = new ServiceFactory();
				}
			}
		}
		return factory;
	}

	public void initialize() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("classpath*:*.xml");
		}
	}

	public <T extends Object> T getService(Services service, Class<T> cls) {

		if (context == null) {
			initialize();
		}
		return (T) context.getBean(service.toString());
	}
}
