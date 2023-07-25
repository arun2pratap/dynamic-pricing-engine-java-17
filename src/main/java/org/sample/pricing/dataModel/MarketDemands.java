package org.sample.pricing.dataModel;

import java.time.LocalDate;

public record MarketDemands(String hotel, LocalDate occupancyDate, Integer hits) {
}
