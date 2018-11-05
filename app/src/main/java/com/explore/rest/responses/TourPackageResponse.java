package com.explore.rest.responses;

import lombok.Getter;
import lombok.Setter;

public class TourPackageResponse {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String region;
    @Getter
    @Setter
    private Double averageReviewScore;
    @Getter
    @Setter
    private String id;
}
