package com.asp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.asp.constants.FrameworkConstants;
import com.asp.enums.ConfigProperties;
import com.asp.exceptions.PropertyFileUsageException;

/**
 * Read the property file and store it in a HashMap for faster processing.
 * 
 * @author Anjan S P
 */
public final class PropertyUtils {

	private static Properties property = new Properties();
	private static final Map<String, String> CONFIGMAP = new HashMap<>();

	/**
	 * Private constructor to avoid external instantiation
	 */
	private PropertyUtils() {
	}

	static {
		try (FileInputStream file = new FileInputStream(FrameworkConstants.getConfigFilePath())) {
			property.load(file);
			for (Map.Entry<Object, Object> entry : property.entrySet()) {
				CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Receives the {@link com.asp.enums.ConfigProperties},converts to lower case ,
	 * return the corresponding value for the key from the HashMap
	 * 
	 * @author Anjan S P
	 * @param key To be fetched from property file
	 * @return corresponding value for the requested key if found else
	 *         {@link PropertyFileUsageException}
	 */
	public static String get(ConfigProperties key) {
		if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
			throw new PropertyFileUsageException(
					"Property name " + key + " is not found. Please check config.properties");
		}
		return CONFIGMAP.get(key.name().toLowerCase());
	}

}
