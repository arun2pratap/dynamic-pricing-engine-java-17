package org.sample.pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pricing")
public class PricingController {
    private final PricingEngine pricingEngine;

    @Autowired
    public PricingController(PricingEngine pricingEngine) {
        this.pricingEngine = pricingEngine;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculateFinalPrice(@RequestParam double basePrice,
                                                      @RequestParam String customerType,
                                                      @RequestParam int orderQuantity) {
        Map<String, Object> additionalData = new HashMap<>();
        additionalData.put("customerType", customerType);
        additionalData.put("orderQuantity", orderQuantity);

        double finalPrice = pricingEngine.calculateFinalPrice(basePrice, additionalData);
        return ResponseEntity.ok(finalPrice);
    }
}
