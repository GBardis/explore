package com.explore.features.tour.Observers.TourObservers;

import com.explore.features.tour.domain.TourDomain;

import java.util.ArrayList;
import java.util.List;

public class ObservableTourList extends TourObservable {
    List<TourDomain> tourDomainList = new ArrayList<>();

    public List<TourDomain> getTourDomainList() {
        return tourDomainList;
    }

    public void setTourDomainList(List<TourDomain> tourDomainList) {
        this.tourDomainList = tourDomainList;
    }


    public void changeDataset(List<TourDomain> tourDomainList) {
        this.tourDomainList = tourDomainList;
        setChanged();
        notifyObservers(tourDomainList);
    }
}
