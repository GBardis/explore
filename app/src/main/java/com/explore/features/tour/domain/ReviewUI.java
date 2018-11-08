package com.explore.features.tour.domain;

import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

public class ReviewUI {
    @Getter
    @PrimaryKey
    private final String id;

    @Setter
    @Getter
    private String comment;

    @Setter
    @Getter
    private int score;

    @Getter
    @Setter
    private String username;

    public ReviewUI(String id, String comment, int score, String username) {
        this.id = id;
        this.comment = comment;
        this.score = score;
        this.username = username;
    }
}
