package com.hungry.services;

import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hungry.repositories.SaleRepository;
import com.hungry.services.util.SecurityMaster;

@Service
public class SaleService {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(SaleService.class);

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private SecurityMaster securityMaster;

	@Transactional
	public ResponseEntity<Void> sale(String token, int productId, int peiecs) {

		try {
			double price = saleRepository.findProductPrice(productId);
			double totalSoldPrice = saleRepository.findProductSoldPrice(productId);
			int totalSoldPeices = saleRepository.findProductSoldPeices(productId);
			String buyers = saleRepository.findProductBuyers(productId);

			Map<String, Object> mapper = securityMaster.decrypt(token);

			long userId = (long) mapper.get("user_id");

			/**
			 * Order work must be finished
			 */

			if (buyers == null)
				buyers = "[{ \"user_id\" : " + userId + ",\"orders\":[" + 1 + "]" + " }]";

			else {
				JSONArray list = new JSONArray(buyers);
				if (!list.isEmpty()) {
					for (int i = 0; i < list.length(); ++i) {
						JSONObject object = list.getJSONObject(i);
						int id = object.getInt("user_id");
						if (id == userId) {
							JSONArray orders = object.getJSONArray("orders");
							// orders id put
							orders.put(2);
							buyers = "[{ \"user_id\" : " + userId + ",\"orders\":" + orders.toString() + " }]";
							break;
						}
					}
				}

				LOG.debug("buyers : " + buyers);
			}

			// buyers = "[{ \"user_id\" : " + userId + ",\"orders\":[" + 1 + "]" + " }]";

			double newSoldPrice = totalSoldPrice + peiecs * price;
			int newSoldPeices = totalSoldPeices + peiecs;

			LOG.info("sale :  newSoldPrice : " + newSoldPrice + " newSoldPeices : " + newSoldPeices
					+ " totalSoldPrice : " + totalSoldPrice + " totalSoldPeices : " + totalSoldPeices);
			saleRepository.update(newSoldPrice, newSoldPeices, buyers, productId);

			return ResponseEntity.accepted().body(null);

		} catch (JSONException e) {

			LOG.error("sale :  JSONException " + e.getMessage());
		}

		catch (Exception e) {

			LOG.error("sale :  JSONException " + e.getMessage());
		}
		return ResponseEntity.badRequest().body(null);
	}

}
