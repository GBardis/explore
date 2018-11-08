package com.explore.base;

import com.explore.features.reviewnew.observers.ReviewObservable;
import com.explore.features.reviewnew.observers.ReviewObserver;
import com.explore.features.tour.observers.TourObservable;
import com.explore.features.tour.observers.TourObserver;
import com.explore.features.tourpackage.data.TourPackageObservable;
import com.explore.features.tourpackage.data.TourPackageObserver;
import com.explore.features.user.data.UserObservable;
import com.explore.features.user.data.UserObserver;

public abstract class PresenterObserver implements TourPackageObserver, TourObserver, ReviewObserver, UserObserver {
    @Override
    public void updateTourPackageList(TourPackageObservable tourPackageObservable, Object o) {

    }

    @Override
    public void updateToursList(TourObservable tourObservable, Object o) {

    }

    @Override

    public void updateReviewsList(ReviewObservable reviewObservable, Object o) {

    }

    @Override
    public void updateUsersList(UserObservable userObservable, Object o) {

    }

}
