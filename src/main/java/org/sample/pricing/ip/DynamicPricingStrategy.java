package org.sample.pricing.ip;

import org.sample.pricing.client.SubscriberDataService;
import org.sample.pricing.dataModel.CompetitorRate;
import org.sample.pricing.dataModel.Hotel;
import org.sample.pricing.dataModel.MarketDemands;
import org.sample.pricing.dataModel.PriceRequestDto;
import org.sample.pricing.persist.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Component
public final class DynamicPricingStrategy implements PricingStrategy {

    private static final String WEIGHTED_AVG_COMPETITOR_RATE = "weightedAvgCompetitorRate";
    private static final String AVG_CORRELATION_WEIGHT = "avgCorrelationWeight";
    private final SubscriberDataService subscriberDataService;
    private HotelService hotelService;
    @Autowired
    public DynamicPricingStrategy(SubscriberDataService subscriberDataService){
        this.subscriberDataService = subscriberDataService;
    }

    @Override
    public double calculatePrice(PriceRequestDto priceRequestDto, Function5 function) {
        Hotel hotel = hotelService.getHotel(priceRequestDto.hotelName());
        List<CompetitorRate> competitorRates = subscriberDataService.getCompetitorRates(priceRequestDto.hotelName(), priceRequestDto.checkIn(), priceRequestDto.checkOut());
        List<MarketDemands> marketDemands = subscriberDataService.getMarketDemands(priceRequestDto.hotelName(), priceRequestDto.checkIn(), priceRequestDto.checkOut());
        OptionalDouble averageDemand = marketDemands.stream().mapToInt(MarketDemands::hits).average();
        Map<String, Double> competitorMap = competitorRates.stream().collect(Collectors.teeing(Collectors.averagingDouble(rate -> rate.rate() * rate.correlationWeight()),
                Collectors.averagingDouble(CompetitorRate::correlationWeight),
                (e1, e2) -> {
                    Map<String, Double> map = new HashMap<>();
                    map.put(WEIGHTED_AVG_COMPETITOR_RATE, e1);
                    map.put(AVG_CORRELATION_WEIGHT, e2);
                    return map;
                }));
        double proportionateDemand = averageDemand.orElseThrow(() -> new RuntimeException("Invalid Demand values")) / 100;


        return Double.valueOf(function.apply(competitorMap.get(AVG_CORRELATION_WEIGHT), proportionateDemand, hotel.basePrice(), hotel.name()).toString()).doubleValue();
    }
}