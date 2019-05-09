package com.hungry.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hungry.configs.ApplicationConfigReader;
import com.hungry.entities.User;
import com.hungry.rabbitmq.MessageSender;
import com.hungry.rabbitmq.services.ApplicationConstant;
import com.hungry.rabbitmq.services.MesageSendService;
import com.hungry.rabbitmq.services.MessageListener;
import com.hungry.services.UserService;

@RestController
public class RabbitMQController {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	private final RabbitTemplate rabbitTemplate;
	private ApplicationConfigReader applicationConfig;
	private MessageSender messageSender;

	@Autowired
	private MesageSendService mesageSendService;

	@Autowired
	private MessageListener messageListener;

	public ApplicationConfigReader getApplicationConfig() {
		return applicationConfig;
	}

	@Autowired
	public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	@Autowired
	public RabbitMQController(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public MessageSender getMessageSender() {
		return messageSender;
	}

	@Autowired
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendMessage(@RequestBody User user) {

		String exchange = getApplicationConfig().getApp2Exchange();
		String routingKey = getApplicationConfig().getApp2RoutingKey();

		/* Sending to Message Queue */
		try {

			mesageSendService.send("Hello");

			// messageSender.sendMessagePublicCorpus(rabbitTemplate, exchange,
			// routingKey,"http://localhost:8081/add", user);
			messageSender.sendMessage(rabbitTemplate, exchange, routingKey, user);
			return new ResponseEntity<String>(ApplicationConstant.IN_QUEUE, HttpStatus.OK);

		} catch (Exception ex) {
			log.error("Exception occurred while sending message to the queue. Exception= {}", ex);
			return new ResponseEntity(ApplicationConstant.MESSAGE_QUEUE_SEND_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
