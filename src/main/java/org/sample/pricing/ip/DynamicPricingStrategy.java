package org.sample.pricing.ip;

import lombok.RequiredArgsConstructor;
import org.sample.pricing.client.SubscriberDataService;
import org.sample.pricing.dataModel.CompetitorRate;
import org.sample.pricing.dataModel.MarketDemands;
import org.sample.pricing.dataModel.PriceRequestDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public final class DynamicPricingStrategy implements PricingStrategy {

    private final SubscriberDataService subscriberDataService;
    @Override
    public double calculatePrice(PriceRequestDto priceRequestDto) {
        List<CompetitorRate> competitorRates = subscriberDataService.getCompetitorRates(priceRequestDto.hotelName(), priceRequestDto.checkIn(), priceRequestDto.checkOut());
        List<MarketDemands> marketDemands = subscriberDataService.getMarketDemands(priceRequestDto.hotelName(), priceRequestDto.checkIn(), priceRequestDto.checkOut());
        return basePrice * dynamicFactor;
    }
}