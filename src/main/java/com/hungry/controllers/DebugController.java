package com.hungry.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hungry.entities.Order;
import com.hungry.repositories.DbManagerRepository;
import com.hungry.repositories.OrderRepository;
import com.hungry.repositories.UserRepository;

@Controller
public class DebugController {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private DbManagerRepository DbManagerRepository;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody String start_up() {
		return "program start '.' ";
	}

	@GetMapping(value = "/debug")
	public @ResponseBody ResponseEntity<Object> debug() {

		 DbManagerRepository.execution();

		 userRepository.deleteUserByPhone("01686654729");
		 
		List<Order> orders = orderRepository.findOrderByUserAndProductId();
		return new ResponseEntity<Object>(orders, HttpStatus.OK);
	}

}
