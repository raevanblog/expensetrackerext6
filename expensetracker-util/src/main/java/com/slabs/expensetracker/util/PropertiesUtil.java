package com.slabs.expensetracker.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.slabs.expensetracker.util.exception.UtilityException;

public class PropertiesUtil {

	/**
	 * 
	 * @param fileName
	 *            - File from which properties should be loaded
	 * @return {@link Properties} - Properties loaded from file
	 * @throws UtilityException
	 *             throws {@link UtilityException}
	 */
	public static Properties getFromClassPath(String fileName) throws UtilityException {
		Properties properties = new Properties();
		try {
			properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
		} catch (IOException e) {
			throw new UtilityException("Exception occurred while loading properties " + fileName, e);
		}
		return properties;
	}

	/**
	 * *
	 *
	 * This method returns a <code>{@link Properties}</code> object for the
	 * corresponding property file and also populates the static properties map.
	 *
	 * @param file
	 *            {@link File} - File from which properties should be loaded
	 * @return Properties {@link Properties} - Properties loaded from file
	 * @throws UtilityException
	 *             throws {@link UtilityException}
	 * 
	 *
	 */
	public static Properties getFromFile(File file) throws UtilityException {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			throw new UtilityException("Exception occurred while loading properties " + file.getName(), e);
		}
		return properties;
	}

}
