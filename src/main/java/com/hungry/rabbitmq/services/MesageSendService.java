package com.hungry.rabbitmq.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesageSendService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(String msg) {

		
		rabbitTemplate.convertAndSend("spring-boot-exchange2","*",msg);
	}

}
