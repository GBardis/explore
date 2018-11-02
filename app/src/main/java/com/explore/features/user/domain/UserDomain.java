package com.explore.features.user.domain;

import lombok.Getter;
import lombok.Setter;

public class UserDomain {
    @Getter
    @Setter
    private String password;


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
    private int age;

    public UserDomain(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public UserDomain(String username, String firstName, String lastName, String email, String address, int age) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.age = age;
    }
}
