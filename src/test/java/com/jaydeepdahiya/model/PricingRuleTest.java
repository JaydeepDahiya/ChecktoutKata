package com.jaydeepdahiya.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PricingRuleTest {

    @Test
    void testPricingRuleWithoutOffer() {
        PricingRule pricingRule = new PricingRule("A", 30);

        assertEquals(0, pricingRule.priceForQuantity(0));
        assertEquals(150, pricingRule.priceForQuantity(5));
        assertEquals(300, pricingRule.priceForQuantity(10));
    }

    @Test
    void testPricingRuleWithOffer() {
        PricingRule pricingRule = new PricingRule("A", 50, new Offer(3, 120));

        assertEquals(50, pricingRule.priceForQuantity(1));
        assertEquals(100, pricingRule.priceForQuantity(2));
        assertEquals(120, pricingRule.priceForQuantity(3));
        assertEquals(170, pricingRule.priceForQuantity(4));
        assertEquals(240, pricingRule.priceForQuantity(6));
    }

}
