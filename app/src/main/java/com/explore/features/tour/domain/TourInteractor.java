package com.explore.features.tour.domain;

import com.explore.data.db.model.Tour;
import com.explore.data.db.model.TourPackage;

import java.util.ArrayList;

public interface TourInteractor {

    void getTourPackage(OnTourPackageFinishListener tourPackageFinishListener,String tourPackageId);

    void getTours(ArrayList<TourDomain> tourDomainArrayList);

    interface OnTourPackageFinishListener{
        void onSuccess(ArrayList<TourDomain> tourDomainArrayList,TourPackageDomain tourPackageDomain);

        void onError();
    }
}
