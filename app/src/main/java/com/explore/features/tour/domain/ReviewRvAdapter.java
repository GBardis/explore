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

    private int mExpandedPosition = -1;
    int previousExpandedPosition;

    public ReviewRvAdapter() {
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.recycler_item_review_title)
        public TextView mTextViewReviewTitle;

        @BindView(R.id.recycler_item_review_text)
        public TextView mTextViewReviewText;

        @BindView(R.id.recycler_item_review_score)
        public TextView mTextViewScoreText;

        public ReviewViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public ReviewRvAdapter(ArrayList<ReviewUI> mReviewUIDataset) {
        this.mReviewUIDataset = mReviewUIDataset;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tour_review_view_item, viewGroup, false);

        return new ReviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder,final int position) {
        reviewViewHolder.mTextViewReviewTitle.setText(mReviewUIDataset.get(position).getUsername());
        reviewViewHolder.mTextViewScoreText.setText(String.valueOf(mReviewUIDataset.get(position).getScore()));

        final boolean isExpanded = position==mExpandedPosition;
//        tourViewHolder.mTextViewDescription.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        reviewViewHolder.mTextViewReviewText.setText(!isExpanded?mReviewUIDataset.get(position).getShortComment():mReviewUIDataset.get(position).getComment());
        reviewViewHolder.itemView.setActivated(isExpanded);

        if (isExpanded)
            previousExpandedPosition = position;

        reviewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:position;
                notifyItemChanged(previousExpandedPosition);
                notifyItemChanged(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mReviewUIDataset.size();
    }

}
