package com.explore.features.tour.domain;

import lombok.Getter;
import lombok.Setter;

public class TourUI {
    @Getter
    @Setter
    public final int id;
    @Getter
    @Setter
    public String title;
    @Getter
    @Setter
    public String description;
    @Getter
    @Setter
    public int price;
    @Getter
    @Setter
    public String duration;
    @Getter
    @Setter
    public String bullets;
    @Getter
    @Setter
    public String keywords;

    public String getShortDescription() {
        if (description.length() > 30){
            return description.substring(0,29) + "...";
        } else {
            return description;
        }
    }

    @Setter

    public String shortDescription;



    public TourUI(int id, String title, String description, int price, String duration, String bullets, String keywords) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.bullets = bullets;
        this.keywords = keywords;
    }
}
