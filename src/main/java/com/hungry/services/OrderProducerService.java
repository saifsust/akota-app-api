package com.hungry.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("orderProducerService")
public class OrderProducerService {

	public ResponseEntity<Void> producer(JSONObject jsonObject) {

		if (!jsonObject.has("orders"))
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		if (jsonObject.has("order_type")) {
			String orderType = jsonObject.getString("order_type");
			if (orderType == null)
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			if (orderType.equalsIgnoreCase("home delevery")) {
				if (!jsonObject.has("delevery"))
					return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}
		
		JSONObject place = jsonObject.getJSONObject("delevery");

		JSONArray orders = jsonObject.getJSONArray("orders");

		return null;
	}

}
