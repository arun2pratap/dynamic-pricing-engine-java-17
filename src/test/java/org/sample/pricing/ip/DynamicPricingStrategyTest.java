package org.sample.pricing.ip;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class DynamicPricingStrategyTest {

    @Test
    public void testCalculatePrice() {
        PricingStrategy pricingStrategy = new DynamicPricingStrategy();
        double basePrice = 100.0;
        double expectedFinalPrice = 120.0;

        Map<String, Object> additionalData = new HashMap<>();

        double finalPrice = pricingStrategy.calculatePrice(basePrice, additionalData);
        assertEquals(expectedFinalPrice, finalPrice, 0.001);
    }
}
