package org.sample.pricing.ip;

import java.util.Map;

public interface PricingStrategy {
    double calculatePrice(double basePrice, Map<String, Object> additionalData);
}
