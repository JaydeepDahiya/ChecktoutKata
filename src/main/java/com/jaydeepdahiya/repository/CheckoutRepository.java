package com.jaydeepdahiya.repository;

import java.util.Set;

import com.jaydeepdahiya.model.PricingRule;

public class CheckoutRepository {

	private Set<PricingRule> pricingRules;

	// This method should ideally get the pricing rules from database and then send back the values
	public Set<PricingRule> getPricingRules() {
		return pricingRules;
	}

	// Only implemented for testing purpose to initialize the values
	public void setPricingRules(Set<PricingRule> pricingRules) {
		this.pricingRules = pricingRules;
	}

}
