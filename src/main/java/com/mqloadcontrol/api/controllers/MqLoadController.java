package com.mqloadcontrol.api.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mqloadcontrol.api.base.RestControllerBase;
import com.mqloadcontrol.api.model.RequestObjList;
import com.mqloadcontrol.api.model.ResponseError;
import com.mqloadcontrol.api.model.ResponseSuccess;
import com.mqloadcontrol.api.properties.PropertiesUtils;
import com.mqloadcontrol.api.rabbit.services.ReceiverService;
import com.mqloadcontrol.api.rabbit.services.SenderService;

@RestController
@RequestMapping("/api/mqloadcontrol")
public class MqLoadController extends RestControllerBase {
	
	static Logger logger = LoggerFactory.getLogger(MqLoadController.class);
	
	@Autowired
	private SenderService senderService;
	
	@Autowired
	private ReceiverService receiverService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/{queueName}")
	public ResponseEntity<Object> senderListMq(@PathVariable("queueName") String queueName, @RequestBody RequestObjList<?> obj) {
		
		logger.info("METHOD: sendListMq");
		String msg = null;
		
		try {
			this.senderService.send(queueName, obj.getList());
			msg = "Lista foi envida com sucesso";
			logger.info(msg);
			return this.getResposeSuccess(new ResponseSuccess(msg, 200));
		} catch (Exception e) {
			msg = "Erro ao envia a lista";
			logger.info(msg);
			e.printStackTrace();
			return this.getResposeError(new ResponseError(msg, 500));
		}
		
	}
	
	@GetMapping(value = "/{queueName}")
	public ResponseEntity<Object> receiverListMq(@PathVariable("queueName") String queueName) throws IOException {
		
		logger.info("METHOD: receiverListMq");
		String msg = null;
		
		try {
			receiverService.receiver(queueName);
			msg = "Lista recebida com sucesso";
			logger.info(msg);
			return this.getResposeSuccess(new ResponseSuccess(msg, 200));
		} catch (Exception e) {
			msg = "Erro ao receber a lista";
			logger.info(msg);
			e.printStackTrace();
			return this.getResposeError(new ResponseError(msg, 500));
		}
		
	}

}
 