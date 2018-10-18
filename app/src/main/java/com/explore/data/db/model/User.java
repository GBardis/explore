package com.explore.data.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User {

    @PrimaryKey
    @Getter
    public final int id;
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String password;
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
    private String teamColor;

    public User(int id, String userName, String password, String firstName, String lastName,
                String email, String address, Integer age, String teamColor) {
        this.id = id;
        this.setUserName(userName);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setAddress(address);
        this.setAge(age);
        this.setTeamColor(teamColor);
    }
}
