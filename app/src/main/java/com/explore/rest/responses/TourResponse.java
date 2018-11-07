package com.explore.rest.responses;

import lombok.Getter;
import lombok.Setter;

public class TourResponse {

    @Getter
    @Setter
    int id;
    @Getter
    @Setter
    String title;
    @Getter
    @Setter
    String description;
    @Getter
    @Setter
    int price;
    @Getter
    @Setter
    String duration;
    @Getter
    @Setter
    String bullets;
    @Getter
    @Setter
    String keywords;
}
