package com.explore.rest.responses;

import lombok.Getter;
import lombok.Setter;

public class ReviewResponse {

    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String comment;
    @Getter
    @Setter
    private String score;
    @Getter
    @Setter
    private String username;
}
