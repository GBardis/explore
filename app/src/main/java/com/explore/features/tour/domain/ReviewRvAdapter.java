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

public class ReviewRvAdapter extends RecyclerView.Adapter<ReviewRvAdapter.ReviewViewHolder> {

    private ArrayList<ReviewUI> mReviewUIDataset;

    public ReviewRvAdapter() {
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.recycler_item_review_title)
        public TextView mTextViewReviewTitle;

        @BindView(R.id.recycler_item_review_text)
        public TextView mTextViewReviewText;

        public ReviewViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }
    }

    public ReviewRvAdapter(ArrayList<ReviewUI> mReviewUIDataset) {
        this.mReviewUIDataset = mReviewUIDataset;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tour_review_view_item,viewGroup,false);

        return new ReviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, int i) {
        reviewViewHolder.mTextViewReviewTitle.setText(mReviewUIDataset.get(i).getReviewTitle());
        reviewViewHolder.mTextViewReviewText.setText(mReviewUIDataset.get(i).getReviewText());
    }

    @Override
    public int getItemCount() {
        return mReviewUIDataset.size();
    }

}
