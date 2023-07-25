package org.sample.pricing.dataModel;

import java.time.LocalDate;

public record PriceRequestDto(LocalDate checkIn, LocalDate checkOut, String hotelName, CustomerType customerType) {
}
