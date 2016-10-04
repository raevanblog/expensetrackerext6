package com.slabs.expense.tracker.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class JSONUtil {

	public static String getJSONString(Object object) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	public static Map<String, String> getMapFromInputStream(InputStream is) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(is, Map.class);
	}

	public static Map<String, String> getMapFromString(String is) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(is, Map.class);
	}

	public static Map<String, String> getMapFromObject(Object is) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(getJSONString(is), Map.class);
	}

	public static <T extends Object> List<T> getListFromJSON(String json, Class<T> cls) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, cls));
	}

}
