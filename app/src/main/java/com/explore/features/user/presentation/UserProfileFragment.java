package com.explore.features.user.presentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.explore.R;
import com.explore.features.user.domain.UserUI;
import com.explore.features.user.domain.UserView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserProfileFragment extends Fragment implements UserView {
    @BindView(R.id.text_profile_fragment_username)
    TextView mTextViewUserName;

    @BindView(R.id.text_profile_fragment_email)
    TextView mTextViewEmail;

    @BindView(R.id.text_profile_fragment_firstname)
    TextView mTextViewFirstName;

    @BindView(R.id.text_profile_fragment_lastname)
    TextView mTextViewLastName;

    @BindView(R.id.text_profile_fragment_address)
    TextView mTextViewAddress;

    public UserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, v);
        UserPresenterImpl userPresenter = new UserPresenterImpl(this);
        userPresenter.getUser();
        return v;
    }

    @Override
    public void showUserList(List<UserUI> userUIList) {

    }

    @Override
    public void showUserProfile(UserUI userUI) {
        mTextViewUserName.setText(userUI.getUserName());
        mTextViewEmail.setText(userUI.getEmail());
        mTextViewUserName.setText(userUI.getUserName());
        mTextViewFirstName.setText(userUI.getFirstName());
        mTextViewLastName.setText(userUI.getLastName());
        mTextViewAddress.setText(userUI.getAddress());
    }
}
