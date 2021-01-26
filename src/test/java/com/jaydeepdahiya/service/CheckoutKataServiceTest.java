package com.jaydeepdahiya.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.jaydeepdahiya.exception.InvalidItemException;
import com.jaydeepdahiya.model.Offer;
import com.jaydeepdahiya.model.PricingRule;
import com.jaydeepdahiya.repository.CheckoutRepository;

class CheckoutKataServiceTest {

	@Test
	void testTotalPrice() {
		Set<PricingRule> pricingRules = new HashSet<>(Arrays.asList(
                new PricingRule("A", 50, new Offer(3, 130)),
                new PricingRule("B", 30, new Offer(2, 45)),
                new PricingRule("C", 20),
                new PricingRule("D", 15)
        ));
        CheckoutRepository checkoutRepository = new CheckoutRepository();
        checkoutRepository.setPricingRules(pricingRules);
		CheckoutService checkoutService = new CheckoutServiceImpl(checkoutRepository);
		
		checkoutService.scan("A");
        assertEquals(50, checkoutService.getTotalPrice());
        
		checkoutService.scan("B");
        assertEquals(80, checkoutService.getTotalPrice());
        
        checkoutService.scan("C");
        assertEquals(100, checkoutService.getTotalPrice());
        
        checkoutService.scan("D");
        assertEquals(115, checkoutService.getTotalPrice());

        checkoutService.scan("A");
        assertEquals(165, checkoutService.getTotalPrice());

        checkoutService.scan("B");
        assertEquals(180, checkoutService.getTotalPrice());

        checkoutService.scan("C");
        checkoutService.scan("C");
        assertEquals(220, checkoutService.getTotalPrice());
        
        checkoutService.scan("D");
        checkoutService.scan("D");
        assertEquals(250, checkoutService.getTotalPrice());
		
        checkoutService.scan("A");        
        assertEquals(280, checkoutService.getTotalPrice());
	}
	    
	@Test
    void testInvalidItem() {
        
        Set<PricingRule> pricingRules = new HashSet<>(Arrays.asList(
        		new PricingRule("A", 50)
        ));

        CheckoutRepository checkoutRepository = new CheckoutRepository();
        checkoutRepository.setPricingRules(pricingRules);
        CheckoutService checkoutService = new CheckoutServiceImpl(checkoutRepository);

		Assertions.assertThrows(InvalidItemException.class, () -> checkoutService.scan("B"));
    }
	
}
