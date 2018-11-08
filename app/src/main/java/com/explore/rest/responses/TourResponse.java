package com.explore.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class TourResponse {
    @SerializedName("id")
    @Expose
    @Getter
    @Setter
    int id;
    @SerializedName("title")
    @Expose
    @Getter
    @Setter
    String title;
    @SerializedName("description")
    @Expose
    @Getter
    @Setter
    String description;
    @SerializedName("price")
    @Expose
    @Getter
    @Setter
    int price;
    @SerializedName("duration")
    @Expose
    @Getter
    @Setter
    String duration;
    @SerializedName("bullets")
    @Expose
    @Getter
    @Setter
    String bullets;
    @SerializedName("keywords")
    @Expose
    @Getter
    @Setter
    String keywords;
}
