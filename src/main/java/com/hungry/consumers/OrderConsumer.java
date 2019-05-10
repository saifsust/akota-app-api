package com.hungry.consumers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hungry.rabbitmq.util.Queues;

@RestController
public class OrderConsumer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RequestMapping(value = "/receiver", method = RequestMethod.GET)
	public ResponseEntity<Object> admin() {

		Object msg = rabbitTemplate.receiveAndConvert(Queues.ADMIN);

		return new ResponseEntity<Object>(msg, HttpStatus.OK);
	}

	

}
