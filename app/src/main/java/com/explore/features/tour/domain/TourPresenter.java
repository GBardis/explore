package com.explore.features.tour.domain;

import com.explore.data.db.model.Tour;
import com.explore.data.db.model.TourPackage;

import java.util.ArrayList;

public interface TourPresenter {

    void getTourPackage(ArrayList<Tour> tourArrayList,TourPackage tourPackage);

}