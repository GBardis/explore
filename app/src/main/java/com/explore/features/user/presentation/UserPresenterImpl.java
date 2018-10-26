package com.explore.features.user.presentation;

import com.explore.features.user.data.UserIteractorImpl;
import com.explore.features.user.domain.UserDomain;
import com.explore.features.user.domain.UserIteractor;
import com.explore.features.user.domain.UserPresenter;
import com.explore.features.user.domain.UserUI;
import com.explore.features.user.domain.UserView;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class UserPresenterImpl implements UserPresenter, UserIteractor.OnUserFinishListener, UserIteractor.OnUserListFinishListener {
    @Getter
    @Setter
    UserView userView;
    @Getter
    @Setter
    UserIteractor userIteractor;

    public UserPresenterImpl(UserView userView) {
        this.userView = userView;
        this.userIteractor = new UserIteractorImpl();
    }

    @Override
    public void getUserList() {
        getUserIteractor().getUsers(this);

    }

    @Override
    public void getUser(String userEmail) {
        getUserIteractor().getUser(this, userEmail);
    }

    @Override
    public void onSuccess(List<UserDomain> userDomainList) {
        List<UserUI> userUIList = new ArrayList();
        for (UserDomain userDomain : userDomainList) {
            userUIList.add(new UserUI(userDomain.getUserName(), userDomain.getFirstName(), userDomain.getLastName(), userDomain.getEmail(), userDomain.getAddress(), userDomain.getAge()));
        }
        getUserView().showUserList(userUIList);
    }

    @Override
    public void onSuccess(UserDomain userDomain) {
        getUserView().showUserProfile(new UserUI(userDomain.getUserName(), userDomain.getFirstName(), userDomain.getLastName(), userDomain.getEmail(), userDomain.getAddress(), userDomain.getAge()));
    }

    @Override
    public void onFailure() {

    }
}
