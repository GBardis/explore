package com.explore.features.user.domain;

import android.content.Context;

public interface UserPresenter {

    void getUserList();

    void getUser(String userEmail, String passWord, Context context);
}
