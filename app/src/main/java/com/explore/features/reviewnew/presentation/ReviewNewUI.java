package com.explore.features.reviewnew.presentation;

import lombok.Getter;
import lombok.Setter;

public class ReviewNewUI {
    @Getter
    @Setter
    private String id;

    @Setter
    @Getter
    private String comment;

    @Setter
    @Getter
    private int score;

    @Getter
    @Setter
    private String username;

    public ReviewNewUI(String id, int score, String comment, String username) {
        this.id = id;
        this.comment = comment;
        this.score = score;
        this.username = username;
    }

    public ReviewNewUI(int score, String comment, String username) {
        this.comment = comment;
        this.score = score;
        this.username = username;
    }
}

