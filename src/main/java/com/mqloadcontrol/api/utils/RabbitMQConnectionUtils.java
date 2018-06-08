package com.mqloadcontrol.api.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mqloadcontrol.api.properties.PropertiesUtils;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConnectionUtils {
	
	public static ConnectionFactory createdConnectionFactory() throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(PropertiesUtils.getPropertiesByKey(MqLoadControlConstants.RABBIT_HOSTNAME));
	    factory.setPort(new Integer(PropertiesUtils.getPropertiesByKey(MqLoadControlConstants.RABBIT_HOSTPORT)));
	    factory.setVirtualHost(PropertiesUtils.getPropertiesByKey(MqLoadControlConstants.RABBIT_VIRTUALHOST));
	    factory.setUsername(PropertiesUtils.getPropertiesByKey(MqLoadControlConstants.RABBIT_USERNAME));
	    factory.setPassword(PropertiesUtils.getPropertiesByKey(MqLoadControlConstants.RABBIT_PASSWROD));
	    return factory;
	}

	public static <T> byte[] convertData(final T data) throws JsonProcessingException {
		final ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsBytes(data);
	}

	public static <U> U convertData(final byte[] data, final Class<U> javaType) throws IOException {
		final ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(data, javaType);
	}
}
