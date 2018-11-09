package com.explore.features.tourpackage.domain;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

public class TourPackageUI implements Parcelable {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String region;
    @Getter
    @Setter
    private String ratingColor;
    @Getter
    @Setter
    private String regionColor;
    @Getter
    @Setter
    private Double avgRating;
    @Getter
    @Setter
    private String placeId;

    public TourPackageUI(String id, String name, String region, Double avgRating, String placeId) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.ratingColor = findRatingColor(avgRating);
        this.regionColor = findRegionColor(region);
        this.avgRating = avgRating;
        this.placeId = placeId;
    }

    public TourPackageUI(String id, String name, String region, Double avgRating) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.ratingColor = findRatingColor(avgRating);
        this.regionColor = findRegionColor(region);
        this.avgRating = avgRating;
    }

    protected TourPackageUI(Parcel in) {
        id = in.readString();
        name = in.readString();
        region = in.readString();
        ratingColor = in.readString();
        regionColor = in.readString();
        if (in.readByte() == 0) {
            avgRating = null;
        } else {
            avgRating = in.readDouble();
        }
        placeId = in.readString();
    }

    public static final Creator<TourPackageUI> CREATOR = new Creator<TourPackageUI>() {
        @Override
        public TourPackageUI createFromParcel(Parcel in) {
            return new TourPackageUI(in);
        }

        @Override
        public TourPackageUI[] newArray(int size) {
            return new TourPackageUI[size];
        }
    };

    private String findRatingColor(Double avgRating) {
        if (avgRating < 2) {
            // RED
            return "#FF0000";
        } else if (avgRating > 2 && avgRating <= 3) {
            // YELLOW
            return "#E3FF28";
        } else {
            // GREEN
            return "#13FF16";
        }
    }

    private String findRegionColor(String region) {
        return TourPackageRegion.getRegionColorValue(region);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(region);
        parcel.writeString(ratingColor);
        parcel.writeString(regionColor);
        if (avgRating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(avgRating);
        }
        parcel.writeString(placeId);
    }
}
