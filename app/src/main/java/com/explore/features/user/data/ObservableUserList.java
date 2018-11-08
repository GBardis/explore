package com.explore.features.user.data;

import com.explore.features.user.domain.UserDomain;

import java.util.ArrayList;
import java.util.List;

public class ObservableUserList extends UserObservable {
    private List<UserDomain> UserDomainList = new ArrayList<>();

    public List<UserDomain> getUserDomainList() {
        return UserDomainList;
    }

    public void setUserDomainList(List<UserDomain> UserDomainList) {
        this.UserDomainList = UserDomainList;
    }


    public void changeDataset(List<UserDomain> UserDomainList) {
        this.UserDomainList = UserDomainList;
        setChanged();
        notifyObservers(UserDomainList);
    }
}
