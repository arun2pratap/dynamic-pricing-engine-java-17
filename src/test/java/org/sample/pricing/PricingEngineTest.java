package org.sample.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNull;

class PricingEngineTest {

    @InjectMocks
    private PricingEngine pricingEngine;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getPrice() {
        HotePrice price = pricingEngine.getPrice();
        assertNull(price);
    }
}