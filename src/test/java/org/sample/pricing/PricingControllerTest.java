package org.sample.pricing;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PricingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricingEngine pricingEngine;

    @Test
    public void testCalculateFinalPrice() throws Exception {
        double basePrice = 100.0;
        String customerType = "regular";
        int orderQuantity = 50;

        double expectedFinalPrice = 120.0;

        Map<String, Object> additionalData = new HashMap<>();
        additionalData.put("customerType", customerType);
        additionalData.put("orderQuantity", orderQuantity);

        when(pricingEngine.calculateFinalPrice(basePrice, additionalData))
                .thenReturn(expectedFinalPrice);

        mockMvc.perform(get("/pricing/calculate")
                        .param("basePrice", String.valueOf(basePrice))
                        .param("customerType", customerType)
                        .param("orderQuantity", String.valueOf(orderQuantity))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(String.valueOf(expectedFinalPrice)));

        verify(pricingEngine, times(1)).calculateFinalPrice(basePrice, additionalData);
    }
}
