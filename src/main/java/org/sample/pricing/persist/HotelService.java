package org.sample.pricing.persist;

import lombok.experimental.UtilityClass;
import org.sample.pricing.dataModel.Hotel;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class HotelService {

    public Hotel getHotel(String name){
        return switch (name){
            case "Mariott" -> new Hotel("Mariott", 100, 52.0, Collections.singletonList("Hyatt"));
            case "Hyatt" -> new Hotel("Hyatt", 90, 42.0, Collections.singletonList("Mariott"));
            default -> new Hotel("Conrad", 100, 52.0, Collections.singletonList("Hyatt"));
        };
    }
}
