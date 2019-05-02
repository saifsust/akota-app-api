package com.hungry.services;

import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

			buyers = new JSONArray(buyers).toString();

			double newSoldPrice = totalSoldPrice + peiecs * price;
			int newSoldPeices = totalSoldPeices = peiecs;

			LOG.info("sale :  newSoldPrice : " + newSoldPrice + " newSoldPeices : " + newSoldPeices
					+ " totalSoldPrice : " + totalSoldPrice + " totalSoldPeices : " + totalSoldPeices);
			saleRepository.update(newSoldPrice, newSoldPeices, productId);

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
