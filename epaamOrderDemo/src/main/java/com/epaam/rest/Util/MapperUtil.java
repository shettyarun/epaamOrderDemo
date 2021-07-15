package com.epaam.rest.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {

	private static ObjectMapper mapper = new ObjectMapper();
	private MapperUtil() {
		
	}

	public static String convertToJson(Object fromValue) {
		try {
			return mapper.writeValueAsString(fromValue);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
