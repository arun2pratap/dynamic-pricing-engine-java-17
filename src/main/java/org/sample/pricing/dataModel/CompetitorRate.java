package org.sample.pricing.dataModel;

import java.time.LocalDate;

public record CompetitorRate(LocalDate occupancyDate, Double rate, Integer correlationWeight) {
}
