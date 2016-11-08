package com.slabs.expense.tracker.util;

import java.io.IOException;
import java.io.InputStream;
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
	public static String getJSONString(Object object) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> getMapFromInputStream(InputStream is) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(is, Map.class);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> getMapFromString(String is) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(is, Map.class);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> getMapFromObject(Object is) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(getJSONString(is), Map.class);
	}

	public static <T extends Object> List<T> getListFromJSON(String json, Class<T> cls)
			throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json,
				mapper.getTypeFactory().constructCollectionType(List.class, cls));
	}

}
