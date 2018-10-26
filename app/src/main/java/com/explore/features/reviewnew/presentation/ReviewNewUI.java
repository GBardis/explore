package com.explore.features.reviewnew.presentation;

import lombok.Getter;
import lombok.Setter;

public class ReviewNewUI {
    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String reviewMessage;

    @Getter
    @Setter
    private float rating;

    public ReviewNewUI(String title, String reviewMessage, float rating) {
        this.title = title;
        this.reviewMessage = reviewMessage;
        this.rating = rating;
    }
}

