package org.sample.pricing.client;

import org.sample.pricing.dataModel.CompetitorRate;
import org.sample.pricing.dataModel.Hotel;
import org.sample.pricing.dataModel.MarketDemands;
import org.sample.pricing.persist.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class SubscriberDataService {

    private final HotelService hotelService;

    @Autowired
    public SubscriberDataService(HotelService hotelService){
        this.hotelService = hotelService;
    }

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
            case "Hyatt" -> List.of(new CompetitorRate(LocalDate.parse("2023-01-01"), 700D, 1));
            case "Mariott" -> List.of(new CompetitorRate(LocalDate.parse("2023-01-01"), 910D, 1));
            case "Conrad" -> List.of(new CompetitorRate(LocalDate.parse("2023-01-01"), 820D, -1));
            default -> Collections.emptyList();
        };
    }

    private List<MarketDemands> getMarketDemand(String hotelName){
        return switch (hotelName){
            case "Hyatt" -> List.of(new MarketDemands("Hyatt", LocalDate.parse("2023-01-01"), 10));
            case "Mariott" -> List.of(new MarketDemands("Mariott", LocalDate.parse("2023-01-01"), 10));
            case "Conrad" -> List.of(new MarketDemands("Conrad", LocalDate.parse("2023-01-01"), 10));
            default -> Collections.emptyList();
        };
    }

}
