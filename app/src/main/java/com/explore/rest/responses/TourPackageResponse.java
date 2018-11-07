package com.explore.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class TourPackageResponse {
    @Getter
    @Setter
    @SerializedName("name")
    @Expose
    public String name;
    @Getter
    @Setter
    @SerializedName("region")
    @Expose
    public String region;
    @Getter
    @Setter
    @SerializedName("averageReviewScore")
    @Expose
    public Double averageReviewScore;
    @Getter
    @Setter
    @SerializedName("id")
    @Expose
    public String id;
}
