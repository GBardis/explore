package com.explore.features.tour.data;

import com.explore.data.db.model.Tour;
import com.explore.data.db.model.TourPackage;
import com.explore.features.tour.domain.TourInteractor;
import java.util.ArrayList;

public class TourInteractorImpl implements TourInteractor {

    @Override
    public void getTourPackage(OnTourPackageFinishListener tourPackageFinishListener) {
        tourPackageFinishListener.onSuccess(mockTours(),mockTourPackage());
    }

    @Override
    public void getTours(ArrayList<Tour> tourArrayList) {
    }

    private ArrayList<Tour> mockTours() {
        ArrayList<Tour> tourArrayList = new ArrayList<>();

        tourArrayList.add(new Tour("1","Acropolis Museum",4.2,"description1"));
        tourArrayList.add(new Tour("2","Plaka",4.1,"description2"));
        tourArrayList.add(new Tour("3","Monastiraki",3,"description3"));
        tourArrayList.add(new Tour("4","Syntagma Square",2.5,"description4"));
        tourArrayList.add(new Tour("5","Propylaia",2,"description5"));
        tourArrayList.add(new Tour("6","Omonoia",4.4,"description6"));
        return tourArrayList;
    }

    private TourPackage mockTourPackage() {
        return new TourPackage("1","Acropolis",4.5,"red","blue");
    }


}
