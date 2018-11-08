package com.explore.features.reviewnew.observers;

import com.explore.features.reviewnew.domain.ReviewDomain;

import java.util.ArrayList;
import java.util.List;

public class ObservableReviewList extends ReviewObservable {
    List<ReviewDomain> reviewDomainList = new ArrayList<>();

    public List<ReviewDomain> getReviewDomainList() {
        return reviewDomainList;
    }

    public void setReviewDomainList(List<ReviewDomain> reviewDomainList) {
        this.reviewDomainList = reviewDomainList;
    }


    public void changeDataset(List<ReviewDomain> reviewDomainList) {
        this.reviewDomainList = reviewDomainList;
        setChanged();
        notifyObservers(reviewDomainList);
    }
}
