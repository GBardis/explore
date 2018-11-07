package com.explore.features.tour.domain;

import android.content.Context;

import com.explore.features.tourpackage.PresenterObserver;

import java.util.ArrayList;

public interface TourInteractor {

    void getTourList(PresenterObserver presenterObserver, Context context, String tourPackageId);

    interface OnTourListFinishListener{
        void onTourListSuccess(ArrayList<TourDomain> tourDomainArrayList);

        void onError();
    }
}
