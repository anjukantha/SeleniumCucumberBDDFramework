package com.asp.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.asp.constants.FrameworkConstants;
import com.asp.enums.ConfigProperties;
import com.asp.exceptions.PropertyFileUsageException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * To construct the map by the reading the config values from JSON. Not used in
 * this framework but can be leveraged instead of property file based on the
 * requirements
 * 
 * @author Anjan S P
 * @see PropertyUtils
 */
public class JsonUtils {

	private static Map<String, String> map;

	/**
	 * Private constructor to avoid external instantiation
	 */
	private JsonUtils() {

	}

	static {
		try {
			map = new ObjectMapper().readValue(new File(FrameworkConstants.getJsonconfigfilepath()),
					new TypeReference<HashMap<String, String>>() {
					});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(ConfigProperties key) {
		if (Objects.isNull(key) || Objects.isNull(map.get(key.name().toLowerCase()))) {
			throw new PropertyFileUsageException(
					"Property name " + key + " is not found. Please check config.properties");
		}
		return map.get(key.name().toLowerCase());
	}

}
