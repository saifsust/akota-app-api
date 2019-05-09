package com.hungry.controllers;

import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hungry.services.OrderProducerService;

@RestController
public class OrderController {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderProducerService orderProducerService;

	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> order(@RequestParam("token") String token, @RequestBody Map<String, Object> orders) {
		LOG.debug("order : token  " + token);
		LOG.debug("order : orders  " + orders);
		return orderProducerService.producer(new JSONObject(orders));
	}

}
