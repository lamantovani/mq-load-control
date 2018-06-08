package com.mqloadcontrol.api.rabbit.services;

import java.util.List;

public interface SenderService {
	
	public <T> void send(final String queueName, List<T> list) throws Exception;

}
