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

import com.hungry.entities.Order;
import com.hungry.entities.Product;
import com.hungry.entities.User;
import com.hungry.repositories.DbManagerRepository;
import com.hungry.repositories.OrderRepository;
import com.hungry.repositories.ProductRepository;
import com.hungry.repositories.UserRepository;

@Controller
public class DebugController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private DbManagerRepository DbManagerRepository;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody String start_up() {
		return "program start '.' ";
	}

	@GetMapping(value = "/debug")
	public @ResponseBody ResponseEntity<?> debug() {

		//DbManagerRepository.execution();

		User user = userRepository.findUserByUserId(1);
		System.out.println(user);
		Product prod = productRepository.findProductById(1);
		System.out.println(prod);

		Order order = new Order();
		order.setUser(user);
		order.setProduct(prod);
		order.setDelever(user);
		orderRepository.save(order);
		List<Order> orders = orderRepository.findOrderByUserAndProductAndDelever();

		System.out.println(orders);

		return new ResponseEntity<Object>(orders, HttpStatus.OK);
	}

}
