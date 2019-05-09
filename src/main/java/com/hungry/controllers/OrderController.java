package com.hungry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hungry.producer.OrderProducer;

@RestController
public class OrderController {

	@Autowired
	private OrderProducer orderProducer;

	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> order() {
		orderProducer.oderReceiver("Hello World");
		return new ResponseEntity<String>("Order in QUEUE", HttpStatus.OK);
	}

}
