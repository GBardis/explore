package com.explore.features.tour.data;

import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tour.domain.TourPackageDomain;

import java.util.ArrayList;

public class TourInteractorImpl implements TourInteractor {

    @Override
    public void getTourPackage(OnTourPackageFinishListener tourPackageFinishListener,String tourPackageId) {
        tourPackageFinishListener.onSuccess(mockTours(),mockTourPackage());
    }

    @Override
    public void getTours(ArrayList<TourDomain> tourDomainArrayList) {
    }

    private ArrayList<TourDomain> mockTours() {
        ArrayList<TourDomain> tourDomainArrayList = new ArrayList<>();

        tourDomainArrayList.add(new TourDomain("1","Acropolis Museum",4.2,"description1"));
        tourDomainArrayList.add(new TourDomain("2","Plaka",4.1,"description2"));
        tourDomainArrayList.add(new TourDomain("3","Monastiraki",3,"description3"));
        tourDomainArrayList.add(new TourDomain("4","Syntagma Square",2.5,"description4"));
        tourDomainArrayList.add(new TourDomain("5","Propylaia",2,"description5"));
        tourDomainArrayList.add(new TourDomain("6","Omonoia",4.4,"description6"));
        return tourDomainArrayList;
    }

    private TourPackageDomain mockTourPackage() {
        return new TourPackageDomain("1","Acropolis",4.5,"whatever","Attiki");
    }


}
