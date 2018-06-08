package com.mqloadcontrol.api.rabbit.services;

import java.util.List;

public interface ReceiverService {
	
	public <T> List<T> receiver(final String queueName) throws Exception;

}
