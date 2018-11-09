package com.explore.features.user.domain;

import android.content.Context;

public interface UserPresenter {

    void getUserList(Context context);

    void getUser(String userEmail, String passWord, Context context);

    void findLoggedInUser(Context context);
}
