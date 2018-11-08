package com.explore.features.tourpackage;

import com.explore.features.tour.data.TourObservable;
import com.explore.features.tour.data.TourObserver;
import com.explore.features.tourpackage.data.TourPackageObservable;
import com.explore.features.tourpackage.data.TourPackageObserver;

public abstract class PresenterObserver implements TourPackageObserver, TourObserver {
    @Override
    public void updateTourPackageList(TourPackageObservable tourPackageObservable, Object o) {

    }

    @Override
    public void updateToursList(TourObservable tourObservable, Object o) {

    }
}
