package com.explore.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class LoginResponse {
    @Getter
    @Setter
    @SerializedName("id")
    @Expose
    public Integer id;
    @Getter
    @Setter
    @SerializedName("username")
    @Expose
    public String username;
    @Getter
    @Setter
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @Getter
    @Setter
    @SerializedName("lastName")
    @Expose
    public String lastName;
    @Getter
    @Setter
    @SerializedName("email")
    @Expose
    public String email;
    @Getter
    @Setter
    @SerializedName("address")
    @Expose
    public String address;
    @Getter
    @Setter
    @SerializedName("age")
    @Expose
    public Integer age;
    @Getter
    @Setter
    @SerializedName("team")
    @Expose
    public String team;

}