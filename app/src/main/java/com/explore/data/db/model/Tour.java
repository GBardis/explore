package com.explore.data.db.model;

import android.arch.persistence.room.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Tour {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Integer rating;
    @Getter
    @Setter
    private String descTextSize;

    public Tour(String name, Integer rating, String descTextSize) {
        this.setName(name);
        this.setRating(rating);
        this.setDescTextSize(descTextSize);
    }
}
