package org.sample.dynamicpricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNull;

class DynamicPricingEngineTest {

    @InjectMocks
    private DynamicPricingEngine dynamicPricingEngine;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getPrice() {
        HotePrice price = dynamicPricingEngine.getPrice();
        assertNull(price);
    }
}