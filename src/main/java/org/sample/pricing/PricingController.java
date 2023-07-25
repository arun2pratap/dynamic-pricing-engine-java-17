package org.sample.pricing;

import lombok.RequiredArgsConstructor;
import org.sample.pricing.dataModel.PriceRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/pricing")
public class PricingController {
    private final PricingEngine pricingEngine;

    @Autowired
    public PricingController(PricingEngine pricingEngine){
        this.pricingEngine = pricingEngine;
    }
    @GetMapping("/calculate")
    public ResponseEntity<String> calculateFinalPrice(@RequestBody PriceRequestDto priceRequestDto) {
        double finalPrice = pricingEngine.calculateFinalPriceForWalkIn(priceRequestDto);
        NumberFormat fmt1 = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        fmt1.setMaximumFractionDigits(2);
        var text = """
                Dynamic Pricing for %s hotel for Check-in: %s and Check-out: %s is %s
                """;
        String responseString = text.formatted(priceRequestDto.hotelName(),
                priceRequestDto.checkIn(), priceRequestDto.checkOut(),
                fmt1.format(finalPrice));
        return ResponseEntity.ok(responseString);
    }
}
