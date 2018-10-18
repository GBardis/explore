package com.explore.data.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Review {
    @Getter
    @PrimaryKey
    public final String id;
    @Getter
    @Setter
    private int rating;
    @Getter
    @Setter
    private String ratingColor;
    @Getter
    @Setter
    private String comment;

    public Review(String id, int rating, String ratingColor, String comment) {
        this.id = id;
        this.setRating(rating);
        this.setRatingColor(ratingColor);
        this.setComment(comment);
    }
}
