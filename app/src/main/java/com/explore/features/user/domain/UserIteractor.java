package com.explore.features.user.domain;

import java.util.List;

public interface UserIteractor {

    void getUsers(OnUserListFinishListener onUserListFinishListener);

    void getUser(OnUserFinishListener onUserFinishListener, String userEmail);

    interface OnUserListFinishListener {
        void onSuccess(List<UserDomain> userDomainList);

        void onFailure();
    }

    interface OnUserFinishListener {
        void onSuccess(UserDomain userDomain);

        void onFailure();
    }
}
