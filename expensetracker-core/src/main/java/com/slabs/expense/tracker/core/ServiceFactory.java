package com.slabs.expense.tracker.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.slabs.expense.tracker.common.services.Services;

/**
 * {@link ServiceFactory} is a singleton class, which will get the instance of
 * service class.
 * 
 * @author Shyam Natarajan
 *
 */
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
					L.info("Initializing Service Factory...");
					factory = new ServiceFactory();
				}
			}
		}
		return factory;
	}

	/**
	 * This method load the spring context XML and spring will scan for
	 * {@link Component} {@link Service} etc.
	 */
	public void initialize() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("classpath*:*-spring.xml");
		}
	}

	@SuppressWarnings("unchecked")
	public synchronized <T extends Object> T getService(Services service, Class<T> cls) {

		if (context == null) {
			initialize();
		}
		return (T) context.getBean(service.toString());
	}
}
