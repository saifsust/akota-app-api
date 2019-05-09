package com.hungry.rabbitmq.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {


	@RabbitListener(queues = { "spring-boot" })
	public void processOrder(String order) {

		System.out.println(order);
	}

	@RabbitListener(queues = { "spring-boot1" })
	public void processOrder1(String order) {
		System.out.println(order);
	}
	@RabbitListener(queues = { "app1-queue" })
	public void processOrder2(String order) {
		System.out.println(order);
	}

}
