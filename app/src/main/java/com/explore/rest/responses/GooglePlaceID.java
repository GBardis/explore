package com.explore.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GooglePlaceID {
    @SerializedName("place_id")
    @Expose
    public String placeId;
}
