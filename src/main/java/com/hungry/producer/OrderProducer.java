package com.hungry.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hungry.rabbitmq.util.Queues;

@Service
public class OrderProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void oderReceiver(String order) {
		rabbitTemplate.convertAndSend(Queues.EXCHNAGE, "*", order);
	}

}
