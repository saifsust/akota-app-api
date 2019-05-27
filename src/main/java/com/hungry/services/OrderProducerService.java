package com.hungry.services;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hungry.entities.Order;
import com.hungry.entities.Product;
import com.hungry.entities.User;
import com.hungry.rabbitmq.util.Queues;
import com.hungry.repositories.OrderRepository;
import com.hungry.repositories.ProductRepository;
import com.hungry.repositories.UserRepository;
import com.hungry.services.util.SecurityMaster;

@Service("orderProducerService")
public class OrderProducerService {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(OrderProducerService.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SecurityMaster securityMaster;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Transactional
	public ResponseEntity<Void> producer(Principal principal, JSONObject jsonObject) {

		LOG.debug("producer : jsonObject-> " + jsonObject.toString());
		JSONObject delever = null, pickup = null, destination = null;

		if (!jsonObject.has("orders"))
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		if (jsonObject.has("order_type")) {
			String orderType = jsonObject.getString("order_type");
			LOG.debug("producer : orderType-> " + orderType);
			if (orderType == null)
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

			if (orderType.equalsIgnoreCase("home delevery")) {
				if (!jsonObject.has("delevery"))
					return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
				JSONObject delevery = jsonObject.getJSONObject("delevery");
				LOG.debug("producer : delevery-> " + delevery);
				delever = delevery.getJSONObject("delever");
				pickup = delevery.getJSONObject("pickup");
				destination = delevery.getJSONObject("destination");
				/**
				 * Rider impl in future
				 */

			}
		}

		User buyer = userRepository.findUserByPhoneNumber(principal.getName());
		LOG.debug("producer : buyer-> " + buyer);
		JSONArray orders = jsonObject.getJSONArray("orders");
		LOG.debug("producer : orders-> " + orders);

		List<Order> orderList = new ArrayList<Order>();

		for (int i = 0; i < orders.length(); ++i) {
			JSONObject data = orders.getJSONObject(i);
			LOG.debug("producer : data-> " + data);
			Product product = productRepository.findProductById(data.getInt("product_id"));
			LOG.info("producer : product-> " + product);
			int peices = data.getInt("peices");
			Order order = new Order(buyer, product, peices, peices * product.getSummary().getPrice(),
					data.getJSONArray("delvery_type").toString(), LocalDate.now().toString());
			User user = userRepository.findUserByUserId( delever != null?delever.getInt("user_id") :-1);
			order.setDelever(user);
			order.setPickup(pickup != null ? pickup.toString() : "");
			order.setDestination(destination != null ? destination.toString() : "");
			orderList.add(order);

			orderRepository.save(order);
		}
		rabbitTemplate.convertAndSend(Queues.EXCHNAGE, "*", orderList);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
