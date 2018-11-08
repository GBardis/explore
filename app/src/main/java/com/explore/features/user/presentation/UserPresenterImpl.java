package com.explore.features.user.presentation;

import android.content.Context;

import com.explore.base.PresenterObserver;
import com.explore.features.user.data.UserIteractorImpl;
import com.explore.features.user.data.UserObservable;
import com.explore.features.user.domain.UserDomain;
import com.explore.features.user.domain.UserIteractor;
import com.explore.features.user.domain.UserPresenter;
import com.explore.features.user.domain.UserUI;
import com.explore.features.user.domain.UserView;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class UserPresenterImpl extends PresenterObserver implements UserPresenter, UserIteractor.OnUserListFinishListener, UserIteractor.OnfindLoggedInUserFinishListener {
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
    public void getUser(String userEmail, String passWord, Context context) {
        getUserIteractor().getUser(this, userEmail, passWord, context);
    }

    @Override
    public void findLoggedInUser(Context context) {
        getUserIteractor().findLoggedInUser(context, this);
    }

    @Override
    public void onSuccess(List<UserDomain> userDomainList) {
        List<UserUI> userUIList = new ArrayList();
        for (UserDomain userDomain : userDomainList) {
            userUIList.add(new UserUI(userDomain.getUsername(), userDomain.getFirstName(), userDomain.getLastName(), userDomain.getEmail(), userDomain.getAddress(), userDomain.getAge()));
        }
        getUserView().showUserList(userUIList);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void updateUsersList(UserObservable userObservable, Object o) {
        List<UserUI> userUIList = new ArrayList<>();
        for (UserDomain userDomain : (List<UserDomain>) o) {
            userUIList.add(new UserUI(userDomain.getUsername(), userDomain.getFirstName(),
                    userDomain.getLastName(), userDomain.getEmail(),
                    userDomain.getAddress(), userDomain.getAge()));
        }
        getUserView().showUserProfile(userUIList);
    }

    @Override
    public void onSuccess(boolean isLoggedIn) {
        getUserView().skpiLogin();
    }
}
