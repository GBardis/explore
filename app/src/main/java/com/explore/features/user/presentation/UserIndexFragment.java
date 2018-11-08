package com.explore.features.user.presentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.explore.R;
import com.explore.features.user.domain.UserPresenter;
import com.explore.features.user.domain.UserUI;
import com.explore.features.user.domain.UserView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserIndexFragment extends Fragment implements UserView {
    @BindView(R.id.recycler_user_list)
    RecyclerView userListRv;

    UserIndexRvAdapter userIndexRvAdapter;
    UserPresenter userPresenter;

    public UserIndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_index, container, false);
        ButterKnife.bind(this, v);

        userListRv.setHasFixedSize(true);

        userListRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        userListRv.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()),
                DividerItemDecoration.VERTICAL));

        userPresenter = new UserPresenterImpl(this);
        userPresenter.getUserList();
        return v;
    }

    @Override
    public void showUserList(List<UserUI> userUIList) {
        userIndexRvAdapter = new UserIndexRvAdapter(userUIList);
        userListRv.setAdapter(userIndexRvAdapter);

    }

    @Override
    public void showUserProfile(List<UserUI> userUIList) {

    }

    @Override
    public void showLoginError(String message) {

    }
}
