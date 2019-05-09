package com.hungry.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.hungry.rabbitmq.util.Queues;

@Component
public class OrderConsumer {

	@RabbitListener(queues = { Queues.ADMIN })
	public void read(String msg) {
		System.out.println(msg);
	}

	@RabbitListener(queues = { Queues.COOKER })
	public void readCooker(String msg) {
		System.out.println("Cooker : " + msg);
	}

}
