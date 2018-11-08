package com.explore.features.tour.data;

import java.util.ArrayList;

public class TourObservable {
    ArrayList<TourObserver> tourObserverList = new ArrayList<TourObserver>();

    boolean changeFlag = false;

    public void notifyObservers(Object o) {
        if (hasChanged()) {
            for (TourObserver mo : tourObserverList) {
                mo.updateToursList(this, o);
            }
            clearChanged();
        }
    }


    public void addObserver(TourObserver o) {
        tourObserverList.add(o);
    }

    public void removeObserver(TourObserver o) {
        tourObserverList.remove(o);
    }

    public void setChanged() {
        changeFlag = true;
    }

    public boolean hasChanged() {
        return changeFlag;
    }

    protected void clearChanged() {
        changeFlag = false;
    }
}
