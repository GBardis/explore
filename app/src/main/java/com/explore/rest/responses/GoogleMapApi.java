package com.explore.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoogleMapApi {
    @SerializedName("candidates")
    @Expose
    public List<GooglePlaceID> candidates = null;
}
