package com.explore.features.user.domain;

import android.content.Context;

import com.explore.base.PresenterObserver;

import java.util.List;

public interface UserIteractor {

    void getUsers(OnUserListFinishListener onUserListFinishListener);

    void getUser(PresenterObserver presenterObserver, String userName, String passWord, Context context);

    void findLoggedInUser();

    interface OnUserListFinishListener {
        void onSuccess(List<UserDomain> userDomainList);

        void onFailure();
    }

    interface OnUserFinishListener {
        void onSuccess(UserDomain userDomain);

        void onFailure();
    }
}
