package com.explore.features.tour.domain;

import com.explore.data.db.model.Tour;
import com.explore.data.db.model.TourPackage;

import java.util.ArrayList;

public interface TourInteractor {

    void getTourList(OnTourListFinishListener tourPackageFinishListener,String tourPackageId);

    interface OnTourListFinishListener{
        void onTourListSuccess(ArrayList<TourDomain> tourDomainArrayList);

        void onError();
    }
}
