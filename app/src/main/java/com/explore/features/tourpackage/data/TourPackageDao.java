package com.explore.features.tourpackage.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

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

    @Query("UPDATE tourPackages SET image=:image WHERE id = :id")
    public abstract void updateTourPackageImage(byte[] image, String id);

    @Query("SELECT image FROM tourPackages WHERE id=:id")
    public abstract byte[] getTourPackageImage(String id);

    @Transaction
    void updateTourPackagesDb(List<TourPackageDomain> tourPackageDomainList) {
        deleteAllTourPackages();
        insertTourPackages(tourPackageDomainList);
    }
}
