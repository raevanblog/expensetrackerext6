package com.slabs.expense.tracker.web.services.core;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.common.db.entity.ObjectFactory;

/**
 * {@link JAXBContextResolver}
 * 
 * @author Shyam Natarajan
 *
 */
public class JAXBContextResolver implements ContextResolver<JAXBContext> {

	private static final Logger L = LoggerFactory.getLogger(JAXBContextResolver.class);

	public JAXBContext getContext(Class<?> type) {
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(ObjectFactory.class,
					com.slabs.expense.tracker.webservice.response.ObjectFactory.class);
		} catch (JAXBException e) {
			L.error("Exception occurred, { }", e);
		}
		return context;
	}

}
