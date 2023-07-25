package org.sample.pricing.client;

import lombok.RequiredArgsConstructor;
import org.sample.pricing.dataModel.CompetitorRate;
import org.sample.pricing.dataModel.Hotel;
import org.sample.pricing.dataModel.MarketDemands;
import org.sample.pricing.persist.HotelService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDate;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class SubscriberDataService {

    private final HotelService hotelService;
    public List<CompetitorRate> getCompetitorRates(String hotelName, LocalDate checkIn, LocalDate checkOut) {
        Hotel hotel = hotelService.getHotel(hotelName);
        return hotel.competitors().stream().
                <CompetitorRate>mapMulti((v, consumer) -> getCompetitor(checkIn, checkOut, v)
                .forEach(consumer)).toList();
    }

    public List<MarketDemands> getMarketDemands(String hotelName, LocalDate checkIn, LocalDate checkOut) {
        return getMarketDemand(hotelName).stream()
                .filter(marketDemand -> checkIn.isBefore(marketDemand.occupancyDate()) || checkOut.isAfter(marketDemand.occupancyDate()))
                .toList();
    }

    private List<CompetitorRate> getCompetitor(LocalDate checkIn, LocalDate checkOut, String competitor){
        return switch (competitor){
            case "Hyatt" -> List.of(new CompetitorRate(LocalDate.parse(""), 10D, 1));
            case "Mariott" -> List.of(new CompetitorRate(LocalDate.parse(""), 10D, 1));
            case "Conrad" -> List.of(new CompetitorRate(LocalDate.parse(""), 10D, -1));
        };
    }

    private List<MarketDemands> getMarketDemand(String hotelName){
        return switch (hotelName){
            case "Hyatt" -> List.of(new MarketDemands("Hyatt", LocalDate.parse(""), 10));
            case "Mariott" -> List.of(new MarketDemands("Mariott", LocalDate.parse(""), 10));
            case "Conrad" -> List.of(new MarketDemands("Conrad", LocalDate.parse(""), 10));
        };
    }

}
