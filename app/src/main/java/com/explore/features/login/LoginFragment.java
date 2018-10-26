package com.explore.features.login;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.explore.R;
import com.explore.features.user.domain.UserUI;
import com.explore.features.user.domain.UserView;

import java.util.List;

public class LoginFragment extends Fragment implements UserView {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        return v;
    }

    @Override
    public void showUserList(List<UserUI> userUIList) {

    }

    @Override
    public void showUserProfile(UserUI userUI) {

    }
}
