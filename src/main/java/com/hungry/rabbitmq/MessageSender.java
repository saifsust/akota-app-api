package com.hungry.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.hungry.entities.User;

@Component
public class MessageSender {
	private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

	/**
	 * 
	 * @param rabbitTemplate
	 * @param exchange
	 * @param routingKey
	 * @param data
	 */
	public void sendMessage(RabbitTemplate rabbitTemplate, String exchange, String routingKey, Object data) {
		log.info("Sending message to the queue using routingKey {}. Message= {}", routingKey, data);
		// try {

		rabbitTemplate.convertAndSend(exchange, routingKey, data);
		log.info("The message has been sent to the queue.");
		/*
		 * } catch (Exception e) { System.out.println(e.getMessage()); }
		 */
	}

	public void sendMessagePublicCorpus(RabbitTemplate rabbitTemplate, String exchange, String routingKey, String url,
			Object data) {
		// try {
		log.info("Sending message to the queue using routingKey {}.Url={}. Message= {}", routingKey, url, data);
		rabbitTemplate.convertAndSend(exchange, routingKey, data);
		log.info("The message has been sent to the queue.");
		/*
		 * } catch (Exception e) { // TODO: handle exception
		 * System.out.println(e.getMessage()); }
		 */

	}

}
