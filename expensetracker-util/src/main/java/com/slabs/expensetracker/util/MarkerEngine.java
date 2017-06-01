package com.slabs.expensetracker.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expensetracker.util.exception.UtilityException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MarkerEngine {
	
	private static final Logger L = LoggerFactory.getLogger(MarkerEngine.class);

	private static Configuration configuration;

	public static void initialize() {
		L.info("Initializing Marker Engine...");
		MarkerEngine.configuration = createDefaultConfig();
	}

	public static String process(String templateName, Map<String, ? extends Object> model) throws UtilityException {

		StringWriter writer = null;
		if (configuration == null) {
			initialize();
		}

		try {
			Template t = configuration.getTemplate(templateName);
			writer = new StringWriter();

			t.process(model, writer);
			return writer.toString();

		} catch (IOException ex) {
			throw new UtilityException("Exception occurred while processing template", ex);
		} catch (TemplateException ex) {
			throw new UtilityException("Exception occurred while processing template", ex);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					throw new UtilityException("Exception occurred while processing template", ex);
				}
			}
		}

	}

	public static void process(String templateName, Map<String, Object> model, File fileToWrite) throws UtilityException {

		FileWriter writer = null;
		if (configuration == null) {
			initialize();
		}

		Template t;
		try {
			writer = new FileWriter(fileToWrite);
			t = configuration.getTemplate(templateName);
			t.process(model, writer);
		} catch (IOException ex) {
			throw new UtilityException("Exception occurred while processing template", ex);
		} catch (TemplateException ex) {
			throw new UtilityException("Exception occurred while processing template", ex);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					throw new UtilityException("Exception occurred while processing template", ex);
				}
			}
		}

	}

	private static Configuration createDefaultConfig() {

		Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);
		configuration.setClassLoaderForTemplateLoading(MarkerEngine.class.getClassLoader(), "template");
		return configuration;
	}

	public static Configuration getConfiguration() {

		return configuration;
	}

	public static void setConfiguration(Configuration configuration) {

		MarkerEngine.configuration = configuration;
	}

}