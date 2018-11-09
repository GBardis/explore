package com.explore.features.tour.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.explore.features.tour.domain.TourDomain;

import java.util.List;

@Dao
public abstract class TourDao {
    @Insert
    abstract void insertTours(List<TourDomain> tourDomainList);

    @Query("SELECT * FROM tours WHERE tourPackageId=:tourPackageId")
    abstract List<TourDomain> getTours(final String tourPackageId);

    @Query("DELETE FROM tours")
    abstract void deleteAllTours();

    @Transaction
    void updateToursDb(List<TourDomain> tourDomainList) {
        deleteAllTours();
        insertTours(tourDomainList);
    }
}
