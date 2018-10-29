package com.explore.features.reviewnew.domain;

import lombok.Getter;
import lombok.Setter;

public class ReviewDomain {

    @Getter
    @Setter
    private final String id;

    @Setter
    @Getter
    private String reviewTitle;

    @Setter
    @Getter
    private double reviewScore;

    @Getter
    @Setter
    private String reviewText;

    public ReviewDomain(String id, String reviewTitle, double reviewScore, String reviewText) {
        this.id = id;
        this.reviewTitle = reviewTitle;
        this.reviewScore = reviewScore;
        this.reviewText = reviewText;
    }
}
