package com.explore.features.user.data;

import com.explore.features.user.domain.UserDomain;
import com.explore.features.user.domain.UserIteractor;

import java.util.ArrayList;
import java.util.List;

public class UserIteractorImpl implements UserIteractor {

    @Override
    public void getUsers(OnUserListFinishListener onUserListFinishListener) {
        List<UserDomain> userDomainList = new ArrayList<>();
        userDomainList.add(new UserDomain("George12", "George", "Bardis", "email@email.com", "galatsi", 25));
        userDomainList.add(new UserDomain("Giannhs32", "Giannhs", "Bardis", "email@email.com", "galatsi", 35));
        userDomainList.add(new UserDomain("Makis324", "Makis", "Bardis", "email@email.com", "galatsi", 15));
        userDomainList.add(new UserDomain("Stauros345", "Stauros", "Bardis", "email@email.com", "galatsi", 25));
        userDomainList.add(new UserDomain("Spuros345", "Spuros", "Bardis", "email@email.com", "galatsi", 25));
        userDomainList.add(new UserDomain("Nikos3", "Nikos", "Bardis", "email@email.com", "galatsi", 25));
        onUserListFinishListener.onSuccess(userDomainList);
    }

    @Override
    public void getUser(OnUserFinishListener onUserFinishListener) {
        onUserFinishListener.onSuccess(new UserDomain("George12", "George", "Bardis", "email@email.com", "galatsi", 25));
    }
}
