package com.explore.features.user.data;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;

public class UserObservable {
    private ArrayList<UserObserver> tourPackageObserverList = new ArrayList<UserObserver>();

    private boolean changeFlag = false;

    public void notifyObservers(final Object o) {
        if (hasChanged()) {
            for (final UserObserver mo : tourPackageObserverList) {
                final UserObservable tourPackageObservable = this;
                // run on UI thread
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mo.updateUsersList(tourPackageObservable, o);
                    }
                });
            }
            clearChanged();
        }
    }


    public void addObserver(UserObserver o) {
        tourPackageObserverList.add(o);
    }

    public void removeObserver(UserObserver o) {
        tourPackageObserverList.remove(o);
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
