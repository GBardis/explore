package com.explore.features.reviewnew.domain;

import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

public class ReviewDomain {

    @Getter
    @PrimaryKey
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
