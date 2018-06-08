package com.mqloadcontrol.api.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {
	
	static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	
	public static Properties getProp() {
		try {

			Properties props = new Properties();
			FileInputStream file = new FileInputStream("src/main/resources/properties/lo/mqloadcontrol.properties");
			props.load(file);
			return props;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Erro ao carregar o properties");
		}
		
		logger.info("Properties não foi encontrado");
		return null;
		
	}

	public static String  getPropertiesByKey(String key) throws IOException {
		Properties prop = getProp();
		return prop.getProperty(key);
	}

}
