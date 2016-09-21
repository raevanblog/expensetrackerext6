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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author shyam
 */
public class JAXBUtil {

	private static final Logger L = LoggerFactory.getLogger(JAXBUtil.class);

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

	public static String writeXMLStringToFile(Object o, Class className, File directory, String fileName) {

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

	public static String writeXMLStringToNewFile(Object o, Class className, File directory, String fileName) {

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

	private static void writeToFile(Object o, Writer writer, Class<? extends Object> className) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(className);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(o, writer);
	}

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