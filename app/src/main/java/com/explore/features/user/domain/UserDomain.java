package com.explore.features.user.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "users")
public class UserDomain {
    @NonNull
    @PrimaryKey
    public int userId;
    @Getter
    @Setter
    public String password;
    @Getter
    @Setter
    public String username;
    @Getter
    @Setter
    public String firstName;
    @Getter
    @Setter
    public String lastName;
    @Getter
    @Setter
    public String email;
    @Getter
    @Setter
    public String address;
    @Getter
    @Setter
    public int age;
    @Getter
    @Setter
    public boolean loggedIn;

    @Ignore
    public UserDomain(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public UserDomain(int userId, String username, String firstName, String lastName, String email, String address, int age, boolean loggedIn) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.age = age;
        this.loggedIn = loggedIn;
    }
}
