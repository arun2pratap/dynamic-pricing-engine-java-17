package org.sample.pricing.ip;

import org.sample.pricing.dataModel.PriceRequestDto;

public sealed interface PricingStrategy permits DynamicPricingStrategy {
    double calculatePrice(PriceRequestDto priceRequestDto);
}
