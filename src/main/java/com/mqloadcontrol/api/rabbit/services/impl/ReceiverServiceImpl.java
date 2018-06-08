package com.mqloadcontrol.api.rabbit.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mqloadcontrol.api.rabbit.services.ReceiverService;
import com.mqloadcontrol.api.utils.RabbitMQConnectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Service
public class ReceiverServiceImpl implements ReceiverService {
	
	static Logger logger = LoggerFactory.getLogger(ReceiverServiceImpl.class);

	@Override
	public <T> List<T> receiver(final String queueName) throws Exception {
		
		ConnectionFactory factory = RabbitMQConnectionUtils.createdConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		List<T> list = new ArrayList<T>();

		channel.queueDeclare(queueName, true, false, false, null);

		Consumer consumer = new DefaultConsumer(channel) {
			@SuppressWarnings("unchecked")
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				list.add((T) RabbitMQConnectionUtils.convertData(body, Object.class));
				String message = new String(body, "UTF-8");
				logger.info(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
		return list;
	}
	
	private static void doWork(String task) {
		for (char ch : task.toCharArray()) {
			if (ch == '.') {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException _ignored) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

}
