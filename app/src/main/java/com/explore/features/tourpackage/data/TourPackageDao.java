package com.explore.features.tourpackage.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.explore.features.tourpackage.domain.TourPackageDomain;

import java.util.List;

@Dao
public abstract class TourPackageDao {

    @Insert
    abstract void insertTourPackages(List<TourPackageDomain> tourPackageDomainList);

    @Query("SELECT * FROM tourPackages")
    abstract List<TourPackageDomain> getTourPackages();

    @Query("DELETE FROM tourPackages")
    abstract void deleteAllTourPackages();

    @Transaction
    void updateTourPackages(List<TourPackageDomain> tourPackageDomainList) {
        deleteAllTourPackages();
        insertTourPackages(tourPackageDomainList);
    }
}
