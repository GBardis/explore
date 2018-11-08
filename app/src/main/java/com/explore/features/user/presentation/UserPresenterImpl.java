package com.explore.features.user.presentation;

import android.content.Context;

import com.explore.base.PresenterObserver;
import com.explore.features.user.data.UserInteractorImpl;
import com.explore.features.user.data.UserObservable;
import com.explore.features.user.domain.UserDomain;
import com.explore.features.user.domain.UserInteractor;
import com.explore.features.user.domain.UserPresenter;
import com.explore.features.user.domain.UserUI;
import com.explore.features.user.domain.UserView;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class UserPresenterImpl extends PresenterObserver implements UserPresenter, UserInteractor.OnUserListFinishListener, UserInteractor.OnfindLoggedInUserFinishListener {
    @Getter
    @Setter
    UserView userView;
    @Getter
    @Setter
    UserInteractor userIteractor;

    public UserPresenterImpl(UserView userView) {
        this.userView = userView;
        this.userIteractor = new UserInteractorImpl();
    }

    @Override
    public void getUserList(Context context) {
        getUserIteractor().getUsers(this, context);

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
        getUserView().showUserList(userUIList);
    }

    @Override
    public void onSuccess(boolean isLoggedIn) {
        getUserView().skpiLogin();
    }
}
