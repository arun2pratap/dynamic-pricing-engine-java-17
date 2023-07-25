package org.sample.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sample.pricing.ip.PricingStrategy;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PricingEngineTest {
    @Mock
    private PricingStrategy pricingStrategy;
    @InjectMocks
    private PricingEngine pricingEngine;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getPrice() {
        Map<String, Object> additionalData = new HashMap<>();
        additionalData.put("customerType", "customerType");
        additionalData.put("orderQuantity", 10);
        when(pricingStrategy.calculatePrice(10.5, additionalData)).thenReturn(11.0);
        double v = pricingEngine.calculateFinalPrice(10.5, additionalData);
        assertEquals(11.0, v);
    }
}