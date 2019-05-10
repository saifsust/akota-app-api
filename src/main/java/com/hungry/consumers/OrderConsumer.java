package com.hungry.consumers;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.hungry.entities.Order;
import com.hungry.rabbitmq.util.Queues;

@Component
public class OrderConsumer {

	@RabbitListener(queues = { Queues.ADMIN })
	public void admin(List<Order> msg) {
		System.out.println("Admin : " + msg);
	}

	@RabbitListener(queues = { Queues.MANAGER })
	public void manager(List<Order> msg) {
		System.out.println("manager : " + msg);
	}

}
