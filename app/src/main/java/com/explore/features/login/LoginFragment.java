package com.explore.features.login;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.explore.MainActivity;
import com.explore.R;
import com.explore.features.IsToolbarSetter;
import com.explore.features.tourpackage.presentation.TourPackageFragment;
import com.explore.features.user.domain.UserPresenter;
import com.explore.features.user.domain.UserUI;
import com.explore.features.user.domain.UserView;
import com.explore.features.user.presentation.UserPresenterImpl;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements UserView, IsToolbarSetter {
    @BindView(R.id.edit_text_login_fragment_input_username)
    TextInputEditText mTextInputUsername;
    @BindView(R.id.edit_text_login_fragment_input_password)
    TextInputEditText mTextInputPassword;
    @BindView(R.id.button_login_fragment_login)
    Button buttonLogin;
    @BindString(R.string.login_empty_message)
    String emptyMessage;
    @BindString(R.string.not_valid_email_format_error)
    String emailErrorMessage;
    @BindString(R.string.login_fragment_title)
    String loginFragmentTitle;
    UserPresenter userPresenter;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        setToolbarTitle(getActivity(), loginFragmentTitle);
        userPresenter = new UserPresenterImpl(this);

        //Check if user is already LoggedIn
        userPresenter.findLoggedInUser(getActivity());

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mTextInputUsername.getText().toString();
                String userPassword = mTextInputPassword.getText().toString();

                if (validateLoginForm(userName, userPassword)) {
                    userName = "teamBlack";
                    userPassword = "theBlacksw0rd";
                    userPresenter.getUser(userName, userPassword, getActivity());
                }
                try {


                    // Then just use the following:
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });
        return v;
    }

    private Boolean validateLoginForm(String userName, String passWord) {
        if (userName.isEmpty() || passWord.isEmpty()) {
            Toast.makeText(getActivity(), emptyMessage, Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void showUserList(List<UserUI> userUIList) {

    }

    @Override
    public void showUserProfile(List<UserUI> userUIList) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                ((MainActivity) getActivity()).getSupportActionBar().show();
                TourPackageFragment.TourPackageListener tourPackageListener = (TourPackageFragment.TourPackageListener) getActivity();
                tourPackageListener.transitionToTourPackage();
            }
        });
    }

    @Override
    public void skpiLogin() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                ((MainActivity) getActivity()).getSupportActionBar().show();
                TourPackageFragment.TourPackageListener tourPackageListener = (TourPackageFragment.TourPackageListener) getActivity();
                tourPackageListener.transitionToTourPackage();
            }
        });

    }

    @Override
    public void showLoginError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setToolbarTitle(Activity activity, String title) {
        ((MainActivity) activity).setActivityToolbarTitle(title);
    }
}
