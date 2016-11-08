/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slabs.expense.tracker.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link JAXBUtil} - Utility to work with {@link JAXB}
 *
 * @author Shyam Natarajan
 */
public class JAXBUtil {

	private static final Logger L = LoggerFactory.getLogger(JAXBUtil.class);

	/**
	 * This method read the XML string in the specified file and un-marshall it
	 * to the specified class type
	 * 
	 * @param xmlFile
	 *            {@link File} - File to read the XML string
	 * @param className
	 *            {@link Class} - Class name to cast the return object
	 * @return {@link Object} - Object casted to the specified class type
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T getObject(File xmlFile, Class<T> className) {

		try {
			JAXBContext jaxb = JAXBContext.newInstance(className);
			String xml = FileUtil.readFile(xmlFile);
			Unmarshaller u = jaxb.createUnmarshaller();
			Object o = u.unmarshal(new StringReader(xml));
			return (T) o;
		} catch (JAXBException ex) {
			L.error("Error converting XML string to object {}", ex);
		}
		return null;

	}

	/**
	 * This method will un-marshall the specified xml string to an object of
	 * specified class type
	 * 
	 * @param xml
	 *            {@link String} - XML string to un-marshall
	 * @param className
	 *            {@link Class} - Class to be bound to JAXB context
	 * @return {@link Object} - Un-marshalled object
	 */
	@SuppressWarnings("rawtypes")
	public static Object getObject(String xml, Class className) {

		try {
			JAXBContext jaxb = JAXBContext.newInstance(className);
			Unmarshaller u = jaxb.createUnmarshaller();
			Object o = u.unmarshal(new StringReader(xml));
			return o;
		} catch (JAXBException ex) {
			L.error("Error converting XML string to object {}", ex);
		}
		return null;

	}

	/**
	 * This method will marshall the specified object to XML string
	 * 
	 * @param o
	 *            {@link Object} - Object to be marshalled
	 * @param className
	 *            {@link Class} - Class to be bound to JAXB context
	 * @return {@link String} - XML String
	 */
	@SuppressWarnings("rawtypes")
	public static String getXMLString(Object o, Class className) {

		try {
			StringWriter w = new StringWriter();
			JAXBContext jaxb = JAXBContext.newInstance(className);
			Marshaller m = jaxb.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(o, w);
			return w.toString();
		} catch (JAXBException ex) {
			// TODO Auto-generated catch block
			L.error("Error converting object to XML {}", ex);
		}
		return null;
	}

	/**
	 * This method will marshall the specified object and write the XML string
	 * to an existing file
	 * 
	 * @param o
	 *            {@link Object} - Object to be marshalled
	 * @param className
	 *            {@link Class} - Class to be bound to JAXB context
	 * @param directory
	 *            {@link File} - Directory inside which the file has to be
	 *            created
	 * @param fileName
	 *            {@link String} - File name for the file in which the XML
	 *            string will be written
	 * @return {@link String} - Marshalled XML string
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String writeXMLStringToFile(Object o, Class className, File directory,
			String fileName) {

		File file = null;
		Writer writer = null;
		try {
			file = FileUtil.loadFile(directory, fileName);
			writer = new FileWriter(file);
			writeToFile(o, writer, className);
			return FileUtil.readFile(file);
		} catch (IOException ex) {
			L.error("Error reading file {}, exception {}", file.getName(), ex);
		} catch (JAXBException ex) {
			L.error("Error converting object to XML {}", ex);
		} finally {
			try {
				writer.close();
			} catch (IOException ex) {
				L.error("Error closing writer {}", ex);
			}

		}
		return null;

	}

	/**
	 * This method will marshall the specified object and writes the XML string
	 * to a new file
	 * 
	 * @param o
	 *            {@link Object} - Object to be marshalled
	 * @param className
	 *            {@link Class} - Class to be bound to JAXB context
	 * @param directory
	 *            {@link File} - Directory inside which the file has to be
	 *            created
	 * @param fileName
	 *            {@link String} - File name for the file in which the XML
	 *            string will be written
	 * @return {@link String} - Marshalled XML string
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String writeXMLStringToNewFile(Object o, Class className, File directory,
			String fileName) {

		File file = null;
		Writer writer = null;
		try {
			file = FileUtil.createFile(directory, fileName);
			writer = new FileWriter(file);
			writeToFile(o, writer, className);
			return FileUtil.readFile(file);
		} catch (IOException ex) {
			L.error("Error reading file {}, exception {}", file.getName(), ex);
		} catch (JAXBException ex) {
			L.error("Error converting object to XML {}", ex);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException ex) {
				L.error("Error closing writer {}", ex);
			}

		}
		return null;
	}

	/**
	 * This method will marshall the specified object and writes it to the
	 * {@link Writer}
	 * 
	 * @param o
	 *            {@link Object} - Object to be marshalled
	 * @param writer
	 *            {@link Writer} - Writer to which XMl string will be written
	 * @param className
	 *            {@link Class} - Class to be bound to JAXB context
	 * @throws JAXBException
	 *             throws {@link JAXBException}
	 */
	private static void writeToFile(Object o, Writer writer, Class<? extends Object> className)
			throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(className);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(o, writer);
	}

	/**
	 * This method will marshall the specified object and writes the XML string
	 * to the Output Stream (System.out)
	 * 
	 * @param o
	 *            {@link Object} - Object to be marshalled
	 * @param className
	 *            {@link Class} - Class to be bound to JAXB context
	 */
	@SuppressWarnings("rawtypes")
	public static void writeXMLStringToConsole(Object o, Class className) {

		try {
			JAXBContext jaxb = JAXBContext.newInstance(className);
			Marshaller m = jaxb.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(o, System.out);
		} catch (JAXBException ex) {
			// TODO Auto-generated catch block
			L.error("Error converting object to XML {}", ex);
		}
	}
}