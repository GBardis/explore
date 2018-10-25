package com.explore.features.reviewnew.domain;

import lombok.Getter;
import lombok.Setter;

public class ReviewNewDomain {
    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String reviewMessage;

    @Getter
    @Setter
    private float rating;

    public ReviewNewDomain(String title, String reviewMessage, float rating) {
        this.title = title;
        this.reviewMessage = reviewMessage;
        this.rating = rating;
    }
}
