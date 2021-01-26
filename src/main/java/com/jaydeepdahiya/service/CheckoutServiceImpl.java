package com.jaydeepdahiya.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.jaydeepdahiya.exception.InvalidItemException;
import com.jaydeepdahiya.model.PricingRule;
import com.jaydeepdahiya.repository.CheckoutRepository;

public class CheckoutServiceImpl implements CheckoutService {

	private final Map<String, PricingRule> prices;
	private final Map<String, Integer> itemQuantities;

	public CheckoutServiceImpl(CheckoutRepository checkoutRepository) {
		Map<String, PricingRule> priceMap = checkoutRepository.getPricingRules().stream()
				.collect(Collectors.toMap(PricingRule::getSku, Function.identity()));
		this.prices = priceMap;
		this.itemQuantities = new HashMap<>();
	}

	@Override
	public void scan(String sku) {
		if (prices.containsKey(sku)) {
			itemQuantities.putIfAbsent(sku, 0);
			itemQuantities.compute(sku, (s, i) -> ++i);
		} else {
			throw new InvalidItemException();
		}
	}

	@Override
	public int getTotalPrice() {
		return itemQuantities.entrySet().stream().mapToInt(this::totalPriceForSku).sum();
	}

	private int totalPriceForSku(Map.Entry<String, Integer> skuEntry) {
		PricingRule pricingRule = prices.get(skuEntry.getKey());
		return pricingRule.priceForQuantity(skuEntry.getValue());
	}

}
