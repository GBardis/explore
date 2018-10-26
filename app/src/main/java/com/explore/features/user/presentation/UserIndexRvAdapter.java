package com.explore.features.user.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.explore.R;
import com.explore.features.user.domain.UserUI;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;

public class UserIndexRvAdapter extends RecyclerView.Adapter<UserIndexRvAdapter.UserIndexViewHolder> {
    @Getter
    @Setter
    private List<UserUI> userUIList;

    public UserIndexRvAdapter(List<UserUI> userUIList) {
        this.userUIList = userUIList;
    }

    @NonNull
    @Override
    public UserIndexRvAdapter.UserIndexViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.userindex_recycler_view_item, viewGroup, false);
        return new UserIndexRvAdapter.UserIndexViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserIndexRvAdapter.UserIndexViewHolder userIndexViewHolder, int i) {
        userIndexViewHolder.mTextViewAddress.setText(getUserUIList().get(i).getAddress());
        userIndexViewHolder.mTextViewAge.setText(String.valueOf(getUserUIList().get(i).getAge()));
        userIndexViewHolder.mTextViewName.setText(String.format("%s %s", getUserUIList().get(i).getFirstName(), getUserUIList().get(i).getLastName()));
        userIndexViewHolder.mTextViewEmail.setText(getUserUIList().get(i).getEmail());
        Picasso.get()
                .load("https://bulletinboards.1800runaway.org/core/images/default/default_avatar_large.png")
                .into(userIndexViewHolder.mImageViewProfileImage);
    }

    @Override
    public int getItemCount() {
        return userUIList.size();
    }

    public static class UserIndexViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.recycler_item_user_name)
        TextView mTextViewName;

        @BindView(R.id.recycler_item_tour_email)
        TextView mTextViewEmail;

        @BindView(R.id.recycler_item_user_age)
        TextView mTextViewAge;

        @BindView(R.id.recycler_item_user_address)
        TextView mTextViewAddress;

        @BindView(R.id.recycler_item_image_view_user_profile_image)
        ImageView mImageViewProfileImage;


        public UserIndexViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
