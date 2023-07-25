package org.sample.pricing.ip;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DynamicPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice, Map<String, Object> additionalData) {
        double dynamicFactor = 1.2;
        return basePrice * dynamicFactor;
    }
}