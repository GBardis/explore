package com.explore.features.user.domain;

import java.util.List;

public interface UserView {

    void showUserList(List<UserUI> userUIList);

    void showUserProfile(List<UserUI> userUIList);

    void showLoginError(String message);
}
