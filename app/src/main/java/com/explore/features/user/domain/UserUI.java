package com.explore.features.user.domain;

import lombok.Getter;
import lombok.Setter;

public class UserUI {
    @Getter
    @Setter
    private String userName;
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

    public UserUI(String userName, String firstName, String lastName, String email, String address, int age) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.age = age;
    }
}
