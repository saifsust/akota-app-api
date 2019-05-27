package com.hungry.consumers;

import org.springframework.stereotype.Component;

@Component
public class Consumer {

	/* @RabbitListener(queues="ndd") */ 
	public void reply(Object answer) {
		System.out.println(answer);
	}
	
}
