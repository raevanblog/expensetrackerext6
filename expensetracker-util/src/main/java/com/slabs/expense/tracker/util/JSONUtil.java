package com.slabs.expense.tracker.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * {@link JSONUtil} - Utility to work with JSON
 * 
 * @author Shyam Natarajan
 *
 */
public class JSONUtil {

	/**
	 * This method will return the JSON string for the given object
	 * 
	 * @param object
	 *            {@link Object} - Object for which JSON string to be generated
	 * @return {@link String} - JSON string
	 * @throws IOException
	 *             throws {@link IOException}
	 */
	@SuppressWarnings("unchecked")
	public static String getJSONString(Object object) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	/**
	 * This method will return a map of key/value pairs from the JSON in the
	 * {@link InputStream}
	 * 
	 * @param is
	 *            {@link InputStream} - Input Stream from which the JSON string
	 *            will be processed
	 * @return {@link Map} - Map of key/value pairs
	 * @throws IOException
	 *             throws {@link IOException}
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getMapFromInputStream(InputStream is) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(is, Map.class);
	}

	/**
	 * This method will return a map of key/value from the JSON string
	 * 
	 * @param is
	 *            {@link InputStream} - Input Stream from which the JSON string
	 *            will be processed
	 * @return {@link Map} - Map of key/value pairs
	 * @throws IOException
	 *             throws {@link IOException}
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getMapFromString(String is) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(is, Map.class);
	}

	/**
	 * This method will return a map of key/value pairs from the given
	 * {@link Object}
	 * 
	 * @param object
	 *            {@link Object} - Input Stream from which the JSON string will
	 *            be processed
	 * @return {@link Map} - Map of key/value pairs
	 * @throws IOException
	 *             throws {@link IOException}
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getMapFromObject(Object object) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(getJSONString(object), Map.class);
	}

	/**
	 * This method will return a {@link Collection} based on the given
	 * collection class type
	 * 
	 * @param json
	 *            {@link String} - JSON string
	 * @param cls
	 *            {@link Class} - Any class that implements {@link Collection}
	 * @return {@link Collection} - Collection containing JSON key/value
	 * @throws IOException
	 *             throws {@link IOException}
	 */
	public static <T extends Object> List<T> getListFromJSON(String json, Class<T> cls) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, cls));
	}

}
