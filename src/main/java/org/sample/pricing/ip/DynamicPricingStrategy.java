package org.sample.pricing.ip;

import lombok.RequiredArgsConstructor;
import org.sample.pricing.client.SubscriberDataService;
import org.sample.pricing.dataModel.CompetitorRate;
import org.sample.pricing.dataModel.MarketDemands;
import org.sample.pricing.dataModel.PriceRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class DynamicPricingStrategy implements PricingStrategy {

    private final SubscriberDataService subscriberDataService;

    @Autowired
    public DynamicPricingStrategy(SubscriberDataService subscriberDataService){
        this.subscriberDataService = subscriberDataService;
    }

    @Override
    public double calculatePrice(PriceRequestDto priceRequestDto) {
        List<CompetitorRate> competitorRates = subscriberDataService.getCompetitorRates(priceRequestDto.hotelName(), priceRequestDto.checkIn(), priceRequestDto.checkOut());
        List<MarketDemands> marketDemands = subscriberDataService.getMarketDemands(priceRequestDto.hotelName(), priceRequestDto.checkIn(), priceRequestDto.checkOut());
//        return basePrice * dynamicFactor;
        return 1800D;
    }
}