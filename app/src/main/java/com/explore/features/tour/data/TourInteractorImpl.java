package com.explore.features.tour.data;

import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tour.domain.TourInteractor;

import java.util.ArrayList;

public class TourInteractorImpl implements TourInteractor {

    private ArrayList<TourDomain> mockTours() {
        ArrayList<TourDomain> tourDomainArrayList = new ArrayList<>();

        tourDomainArrayList.add(new TourDomain("1","Acropolis Museum",4.2,"description1"));
        tourDomainArrayList.add(new TourDomain("2","Plaka",4.1,"description2"));
        tourDomainArrayList.add(new TourDomain("3","Monastiraki",3,"description3"));
        tourDomainArrayList.add(new TourDomain("4","Syntagma Square",2.5,"description4"));
        tourDomainArrayList.add(new TourDomain("5","Propylaia",2,"description5"));
        tourDomainArrayList.add(new TourDomain("6","Omonoia",4.4,"description6"));
        tourDomainArrayList.add(new TourDomain("7","sad",4.4,"description6"));
        tourDomainArrayList.add(new TourDomain("8","dsgsd",4.4,"description6"));
        tourDomainArrayList.add(new TourDomain("9","sdfsdf",4.4,"description6"));
        tourDomainArrayList.add(new TourDomain("10","asdasd",4.4,"description6"));
        tourDomainArrayList.add(new TourDomain("11","hfjf",4.4,"description6"));
        tourDomainArrayList.add(new TourDomain("12","dgsdg",4.4,"description6"));
        tourDomainArrayList.add(new TourDomain("13","dfghdtg",4.4,"description6"));
        return tourDomainArrayList;
    }

    @Override
    public void getTourList(OnTourListFinishListener tourListFinishListener,String tourPackageId) {
        tourListFinishListener.onTourListSuccess(mockTours());
    }
}
