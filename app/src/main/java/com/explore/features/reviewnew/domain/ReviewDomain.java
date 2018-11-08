package com.explore.features.reviewnew.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.explore.features.tourpackage.domain.TourPackageDomain;

import lombok.Getter;
import lombok.Setter;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "reviews")
public class ReviewDomain {

    @Getter
    @PrimaryKey
    @NonNull
    public String id;
    @Getter
    @NonNull
    @ForeignKey(entity = TourPackageDomain.class,
            parentColumns = "id",
            childColumns = "tourPackageId",
            onDelete = CASCADE)
    public String tourPackageId;

    @Getter
    @Setter
    public String comment;

    @Getter
    @Setter
    public int score;

    @Getter
    @Setter
    public String username;

    public ReviewDomain(@NonNull String id, @NonNull String tourPackageId, String comment, int score, String username) {
        this.id = id;
        this.tourPackageId = tourPackageId;
        this.comment = comment;
        this.score = score;
        this.username = username;
    }

    @Ignore
    public ReviewDomain(int score, String comment, String username) {
        this.comment = comment;
        this.score = score;
        this.username = username;
    }
}
