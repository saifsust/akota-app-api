package com.hungry.controllers;

import java.security.Principal;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hungry.services.OrderProducerService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
public class OrderController {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderProducerService orderProducerService;

	@RequestMapping(path = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> order(Principal principal ,@RequestBody Map<String, Object> orders) {
		LOG.debug("order : orders  " + orders);
		System.out.println(principal.getName());
		return orderProducerService.producer(principal, new JSONObject(orders));
	}

}
