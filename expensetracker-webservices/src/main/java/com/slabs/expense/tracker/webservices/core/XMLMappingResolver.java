package com.slabs.expense.tracker.webservices.core;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.common.database.entity.ObjectFactory;

/**
 * {@link XMLMappingResolver}
 * 
 * @author Shyam Natarajan
 *
 */
public class XMLMappingResolver implements ContextResolver<JAXBContext> {

	private static final Logger L = LoggerFactory.getLogger(XMLMappingResolver.class);

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
