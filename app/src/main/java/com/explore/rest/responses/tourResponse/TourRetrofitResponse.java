package com.explore.rest.responses.tourResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourRetrofitResponse {

    @SerializedName("_embedded")
    @Expose
    public TourEmbeddedResponse embedded;
}
