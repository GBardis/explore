package com.explore.features.tourpackage.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.explore.R;
import com.explore.features.tourpackage.domain.OnTourPackageClickListener;
import com.explore.features.tourpackage.domain.TourPackageUI;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class TourPackagesRvAdapter extends RecyclerView.Adapter<TourPackagesRvAdapter.TourPackagesViewHolder> implements Filterable {
    @Getter
    @Setter
    private List<TourPackageUI> tourPackageList;
    @Getter
    @Setter
    private List<TourPackageUI> tourPackageUIFilteredList;
    @Setter
    @Getter
    private OnTourPackageClickListener onTourPackageClickListener;
    @Getter
    @Setter
    private Context context;

    TourPackagesRvAdapter(List<TourPackageUI> tourPackageList, OnTourPackageClickListener onTourPackageClickListener, Context context) {
        this.setTourPackageList(tourPackageList);
        this.setOnTourPackageClickListener(onTourPackageClickListener);
        this.setContext(context);
        this.setTourPackageUIFilteredList(tourPackageList);
    }


    static class TourPackagesViewHolder extends RecyclerView.ViewHolder {
        TextView mTourPackageName;
        TextView mTourPackageAvgRating;
        RelativeLayout mRelativeLayout;

        TourPackagesViewHolder(@NonNull View itemView) {
            super(itemView);
            mTourPackageName = itemView.findViewById(R.id.text_tourpackage_name);
            mTourPackageAvgRating = itemView.findViewById(R.id.text_tourpackage_avgrating);
            mRelativeLayout = itemView.findViewById(R.id.tourpackage_root);
        }
    }

    @NonNull
    @Override
    public TourPackagesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_tour_package_item, viewGroup, false);

        return new TourPackagesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TourPackagesViewHolder tourPackagesViewHolder, int i) {
        final TourPackageUI tourPackageUI = getTourPackageList().get(i);
        tourPackagesViewHolder.mTourPackageName.setText(tourPackageUI.getName());
        tourPackagesViewHolder.mTourPackageAvgRating.setText(String.valueOf(tourPackageUI.getAvgrating()));

        tourPackagesViewHolder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTourPackageClickListener.onTourPackageClicked(tourPackageUI);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.getTourPackageUIFilteredList().size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    setTourPackageUIFilteredList(getTourPackageList());
                } else {
                    List<TourPackageUI> filteredList = new ArrayList<>();
                    for (TourPackageUI row : tourPackageList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    setTourPackageUIFilteredList(filteredList);
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = getTourPackageUIFilteredList();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                setTourPackageUIFilteredList((ArrayList<TourPackageUI>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
}



