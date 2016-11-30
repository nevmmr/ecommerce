package com.mobapp.factory;

import com.mobapp.model.StripeMethod;
import com.mobapp.model.StripeTransaction;

import java.util.HashMap;
import java.util.Map;

public class StripeTransactionFactory {
	String privateKey;

	public StripeTransaction create(Map params) {

		Map<String, Integer> chargeData = null;
		try {
			Stripe.setApiKey(privateKey);
			chargeData = Charge.create(params);
		} catch (Exception $e) {
			// notify Payment Order Fail
			Map<String, Integer> data = new HashMap<String, Integer>();
			data.put("paid", 0);
			return new StripeTransaction("paid", 0);
		}

		return new StripeTransaction("paid", 1, chargeData);
	}
}
