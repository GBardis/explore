package com.explore.features.tourpackage.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.explore.R;
import com.explore.features.tourpackage.domain.OnTourPackageClickListener;
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
    private OnTourPackageClickListener listener;
    @Getter
    @Setter
    private Context context;

    public TourPackagesRvAdapter(List<TourPackageUI> tourPackageList, OnTourPackageClickListener listener, Context context) {
        this.setTourPackageList(tourPackageList);
        this.setListener(listener);
        this.setContext(context);
    }

    public static class TourPackagesViewHolder extends RecyclerView.ViewHolder {
        TextView mTourPackageName;
        TextView mTourPackageAvgRating;
        RelativeLayout relativeLayout;

        public TourPackagesViewHolder(@NonNull View itemView) {
            super(itemView);
            mTourPackageName = itemView.findViewById(R.id.text_tourpackage_name);
            mTourPackageAvgRating = itemView.findViewById(R.id.text_tourpackage_avgrating);
            relativeLayout = itemView.findViewById(R.id.tourpackage_root);
        }
    }

    @NonNull
    @Override
    public TourPackagesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_tour_package_item, viewGroup, false);

        TourPackagesViewHolder vh = new TourPackagesViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TourPackagesViewHolder tourPackagesViewHolder, int i) {
        final int position = i;
        final TourPackageUI tourPackageUI = getTourPackageList().get(position);
        tourPackagesViewHolder.mTourPackageName.setText(tourPackageUI.getName());
        tourPackagesViewHolder.mTourPackageAvgRating.setText(String.valueOf(tourPackageUI.getAvgrating()));

    }

    @Override
    public int getItemCount() {
        return this.getTourPackageList().size();
    }
}



