package com.explore.features.tour.domain;

import com.explore.data.db.model.Tour;
import com.explore.data.db.model.TourPackage;

import java.util.ArrayList;

public interface TourInteractor {

    void getTourPackage(OnTourPackageFinishListener tourPackageFinishListener);

    void getTours(ArrayList<Tour> tourArrayList);

    interface OnTourPackageFinishListener{
        void onSuccess(ArrayList<Tour> tourArrayList,TourPackage tourPackage);

        void onError();
    }
}
