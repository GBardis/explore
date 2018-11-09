package com.explore.rest.responses.tourResponse;

import com.explore.rest.responses.TourResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TourEmbeddedResponse {
    public List<TourResponse> tours = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
