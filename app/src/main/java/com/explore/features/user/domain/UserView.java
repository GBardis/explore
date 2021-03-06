package com.explore.features.user.domain;

import java.util.List;

public interface UserView {

    void showUserList(List<UserUI> userUIList);

    void skipLogin();

    void showLoginError(String message);
}
