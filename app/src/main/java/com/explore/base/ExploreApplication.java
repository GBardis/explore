package com.explore.base;

import android.app.Application;

import com.explore.features.user.domain.UserDomain;

public class ExploreApplication extends Application {

    private static UserDomain currentUser;

    public static UserDomain getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserDomain user) {
        currentUser = user;
    }

    public ExploreApplication() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
