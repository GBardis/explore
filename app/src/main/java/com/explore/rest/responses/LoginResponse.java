package com.explore.rest.responses;

import lombok.Getter;
import lombok.Setter;

public class LoginResponse {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private Integer age;
    @Getter
    @Setter
    private String team;
}
