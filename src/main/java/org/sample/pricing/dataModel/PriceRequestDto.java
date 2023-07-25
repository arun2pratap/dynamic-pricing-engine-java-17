package org.sample.pricing.dataModel;

import lombok.Builder;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Builder
public record PriceRequestDto(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut, String hotelName, CustomerType customerType) {
}
