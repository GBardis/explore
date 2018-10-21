package com.explore.features.tourpackage.presentation;

import android.support.v7.widget.RecyclerView;

import com.explore.features.tourpackage.domain.OnTourPackageListener;
import com.explore.features.tourpackage.domain.TourPackageUI;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class TourPackagesRvAdapter extends RecyclerView.Adapter<TourPackagesRvAdapter.TourPackagesViewHolder> {
    @Getter
    @Setter
    private List<TourPackageUI> tourPackageList = new ArrayList<>();
    @Setter
    @Getter
    private OnTourPackageListener listener;

    public TourPackagesRvAdapter(List<TourPackageUI> tourPackageList, OnTourPackageListener listener) {
        this.setTourPackageList(tourPackageList);
        this.setListener(listener);
    }
}
 public static class TourPackagesViewHolder extends RecyclerView.ViewHolder{

 }

}
