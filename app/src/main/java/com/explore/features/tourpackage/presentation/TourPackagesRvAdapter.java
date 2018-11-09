package com.explore.features.tourpackage.presentation;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.explore.R;
import com.explore.features.tourpackage.domain.OnTourPackageClickListener;
import com.explore.features.tourpackage.domain.TourPackageUI;
import com.explore.rest.GooglePlacesApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @Getter
    private GooglePlacesApiClient googlePlacesApiClient;

    TourPackagesRvAdapter(List<TourPackageUI> tourPackageList, OnTourPackageClickListener onTourPackageClickListener, Context context) {
        this.tourPackageList = tourPackageList;
        this.onTourPackageClickListener = onTourPackageClickListener;
        this.context = context;
        this.tourPackageUIFilteredList = tourPackageList;
        this.googlePlacesApiClient = new GooglePlacesApiClient(context);
    }


    public static class TourPackagesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_tourpackage_name)
        TextView mTourPackageName;
        @BindView(R.id.text_tourpackage_avgrating)
        TextView mTourPackageAvgRating;
        @BindView(R.id.tourpackage_root)
        LinearLayout mLinearLayout;
        @BindView(R.id.image_tourpackage_photo)
        ImageView mTourPackagePhoto;
        @BindView(R.id.image_tourpackage_rating)
        ImageView mTourPackageRatingImage;

        TourPackagesViewHolder(@NonNull View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @NonNull
    @Override
    public TourPackagesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tour_package_recycler_item, viewGroup, false);
        return new TourPackagesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TourPackagesViewHolder tourPackagesViewHolder, int i) {
        final TourPackageUI tourPackageUI = getTourPackageList().get(i);
        tourPackagesViewHolder.mTourPackageName.setText(tourPackageUI.getName());
        tourPackagesViewHolder.mTourPackageAvgRating.setText(String.valueOf(tourPackageUI.getAvgRating()));
        tourPackagesViewHolder.mTourPackageAvgRating.setTextColor(Color.parseColor(tourPackageUI.getRatingColor()));

        getGooglePlacesApiClient().tourPackageHasImage(tourPackageUI, tourPackagesViewHolder.mTourPackagePhoto,context);

        tourPackagesViewHolder.mTourPackageRatingImage.setImageResource(R.drawable.ic_star_rate);

        tourPackagesViewHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
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
