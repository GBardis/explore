package com.explore.features.tour.domain;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.explore.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TourRvAdapter extends RecyclerView.Adapter<TourRvAdapter.TourViewHolder> {
    private ArrayList<TourUI> mTourUIDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class TourViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.recycler_item_tour_name)
        public TextView mTextViewName;

        @BindView(R.id.recycler_item_tour_description)
        public TextView mTextViewDescription;

        public TourViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }
    }

    public TourRvAdapter(ArrayList<TourUI> TourUIDataset) {
        this.mTourUIDataset = TourUIDataset;
    }

    @NonNull
    @Override
    public TourRvAdapter.TourViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tour_recycler_view_item,viewGroup,false);

        return new TourViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder tourViewHolder, int i) {
        tourViewHolder.mTextViewName.setText(mTourUIDataset.get(i).getName());
        tourViewHolder.mTextViewDescription.setText(mTourUIDataset.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mTourUIDataset.size();
    }
}
