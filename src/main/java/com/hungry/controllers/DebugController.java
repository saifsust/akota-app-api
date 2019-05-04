package com.hungry.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hungry.entities.Order;
import com.hungry.repositories.DbManagerRepository;
import com.hungry.repositories.OrderRepository;

@RestController
@RequestMapping(value = "/debug")
public class DebugController {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private DbManagerRepository DbManagerRepository;

	@GetMapping(value = "/")
	public ResponseEntity<Object> debug() {

		// DbManagerRepository.execution();

		List<Order> orders = orderRepository.findOrderByUserAndProductId();
		return new ResponseEntity<Object>(orders, HttpStatus.OK);
	}

}
