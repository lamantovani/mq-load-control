package com.mqloadcontrol.api.rabbit.services.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mqloadcontrol.api.rabbit.services.SenderService;
import com.mqloadcontrol.api.utils.RabbitMQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service
public class SenderServiceImpl implements SenderService {
	
	static Logger logger = LoggerFactory.getLogger(SenderServiceImpl.class);

	@Override
	public <T> void send(final String queueName, List<T> list) throws Exception {

		ConnectionFactory factory = RabbitMQConnectionUtils.createdConnectionFactory();

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		try {
			
			channel.queueDeclare(queueName, true, false, false, null);
			
			list.stream().forEach(action -> {
				try {
					channel.basicPublish("", queueName, null, RabbitMQConnectionUtils.convertData(action));
				} catch (IOException e) {
					logger.error("Erro ao carregar item no fila");
					throw new RuntimeException(e);
				}
			});
			
			logger.info("Lista foi carregada com sucesso!");
			
		} catch (Exception e) {
			logger.error("Erro ao carregar item no fila");
			throw new RuntimeException(e);
		} finally {
			channel.close();
			connection.close();
		}

	}

}
