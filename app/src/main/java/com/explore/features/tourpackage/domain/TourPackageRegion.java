package com.explore.features.tourpackage.domain;

import lombok.Getter;

public enum TourPackageRegion {
    CRETE("Crete"),
    PELOPONNESE("Peloponnese"),
    MACEDONIA("Mecodonia"),
    THESSALY("Thessaly"),
    THRACE("Threace"),
    AEAGEAN("Aeagean"),
    IOANIAN("Ioannian"),
    STEREA_HELLAS("Sterea_hellas");

    @Getter
    public final String region;

    TourPackageRegion(String region) {
        this.region = region;
    }


    public static String getRegionColorValue(String region) {
        if (region.equals(CRETE.getRegion())) {
            return "#FF7D01";
        } else if (region.equals(PELOPONNESE.getRegion())) {
            return "#E8410C";
        } else if (region.equals(MACEDONIA.getRegion())) {
            return "#4F16FF";
        } else if (region.equals(THESSALY.getRegion())) {
            return "#01FF01";
        } else if (region.equals(AEAGEAN.getRegion())) {
            return "#28FFA1";
        } else if (region.equals(IOANIAN.getRegion())) {
            return "#E8AE0C";
        } else {
            return "#E3FF28";
        }
    }
}


