package com.explore.features.tourpackage.data;

import java.util.ArrayList;

public class TourPackageObservable {
    ArrayList<TourPackageObserver> tourPackageObserverList = new ArrayList<TourPackageObserver>();

    boolean changeFlag = false;

    public void notifyObservers(Object o)
    {
        if (hasChanged())
        {
            for(TourPackageObserver mo : tourPackageObserverList) {
                mo.updateTourPackage(this, o);
            }
            clearChanged();
        }
    }


    public void addObserver(TourPackageObserver o) {
        tourPackageObserverList.add(o);
    }

    public void removeObserver(TourPackageObserver o){tourPackageObserverList.remove(o);}

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
