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
    private String name;
    @Getter
    @Setter
    @SerializedName("region")
    @Expose
    private String region;
    @Getter
    @Setter
    @SerializedName("averageReviewScore")
    @Expose
    private Integer averageReviewScore;
    @Getter
    @Setter
    @SerializedName("id")
    @Expose
    private String id;
}
