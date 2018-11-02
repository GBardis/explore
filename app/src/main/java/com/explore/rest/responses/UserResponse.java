package com.explore.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class UserResponse {
    @Getter
    @Setter
    @SerializedName("id")
    @Expose
    private Integer id;
    @Getter
    @Setter
    @SerializedName("username")
    @Expose
    private String username;
    @Getter
    @Setter
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @Getter
    @Setter
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @Getter
    @Setter
    @SerializedName("email")
    @Expose
    private String email;
    @Getter
    @Setter
    @SerializedName("address")
    @Expose
    private String address;
    @Getter
    @Setter
    @SerializedName("age")
    @Expose
    private Integer age;
    @Getter
    @Setter
    @SerializedName("team")
    @Expose
    private String team;
}
