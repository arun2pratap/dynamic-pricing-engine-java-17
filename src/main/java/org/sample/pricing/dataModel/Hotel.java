package org.sample.pricing.dataModel;

import lombok.NoArgsConstructor;

import java.util.*;
@NoArgsConstructor
public record Hotel(String name, Integer noOfRooms, Double basePrice, List<String> competitors) {
}
