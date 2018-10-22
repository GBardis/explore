package com.explore.features.tour.domain;

import com.explore.data.db.model.Tour;

public class TourDomain extends Tour {
    public TourDomain(String id, String name, double rating, String descTextSize) {
        super(id, name, rating, descTextSize);
    }
}
