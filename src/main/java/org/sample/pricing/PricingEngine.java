package org.sample.pricing;

import org.sample.pricing.dataModel.CustomerType;
import org.sample.pricing.dataModel.PriceRequestDto;
import org.sample.pricing.ip.Function5;
import org.sample.pricing.ip.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricingEngine {
    private final PricingStrategy pricingStrategy;

    @Autowired
    public PricingEngine(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double calculateFinalPriceForWalkIn(PriceRequestDto priceRequestDto) {
        Function5<Double, Double, Double, String, Double> func = (a, b, c, d) -> {
            return a > b ? b * c : a * c;
        };
        Function5<Double, Double, Double, String, Double> func1 = (a, b, c, d) -> {
            return switch (d){
                case "Hyatt","Conrad" -> 50D;
                case "Marriott" -> 70D;
                default -> 0D;
        };
        };
        return pricingStrategy.calculatePrice(priceRequestDto,
                CustomerType.CONTRACT.name().equals(priceRequestDto.customerType()) ? func : func1);
    }


}
