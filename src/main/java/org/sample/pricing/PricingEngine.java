package org.sample.pricing;

import org.sample.pricing.ip.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PricingEngine {
    private final PricingStrategy pricingStrategy;

    @Autowired
    public PricingEngine(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double calculateFinalPrice(double basePrice, Map<String, Object> additionalData) {
        return pricingStrategy.calculatePrice(basePrice, additionalData);
    }
}
