package com.explore.features.user.domain;

import android.content.Context;

import java.util.List;

public interface UserIteractor {

    void getUsers(OnUserListFinishListener onUserListFinishListener);

    void getUser(OnUserFinishListener onUserFinishListener, String userName, String passWord, Context context);

    interface OnUserListFinishListener {
        void onSuccess(List<UserDomain> userDomainList);

        void onFailure();
    }

    interface OnUserFinishListener {
        void onSuccess(UserDomain userDomain);

        void onFailure();
    }
}
