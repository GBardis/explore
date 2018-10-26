package com.explore.features.login;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.explore.R;
import com.explore.features.tourpackage.presentation.TourPackageFragment;
import com.explore.features.user.domain.UserUI;
import com.explore.features.user.domain.UserView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements UserView {
    @BindView(R.id.edit_text_login_fragment_input_email)
    TextInputEditText mTextInputEmail;
    @BindView(R.id.edit_text_login_fragment_input_password)
    TextInputEditText mTextInputPassword;
    @BindView(R.id.button_login_fragment_login)
    Button buttonLogin;
    @BindString(R.string.login_empty_message)
    String emptyMessage;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateLoginForm(mTextInputEmail.getText().toString(), mTextInputPassword.toString())) {
                    TourPackageFragment.TourPackageListener tourPackageListener = (TourPackageFragment.TourPackageListener) getActivity();
                    tourPackageListener.transitionToTourPackage();
                }
            }
        });
        return v;
    }

    private Boolean validateLoginForm(String email, String passoword) {
        if (email.equals("") || passoword.equals("")) {
            Toast.makeText(getActivity(), emptyMessage, Toast.LENGTH_LONG).show();
            return false;
        }
        return validateEmail(email);
    }

    private boolean validateEmail(String email) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mTextInputEmail.setError("enter a valid email address");
            return false;
        } else {
            mTextInputEmail.setError(null);
            return true;
        }
    }

    @Override
    public void showUserList(List<UserUI> userUIList) {

    }

    @Override
    public void showUserProfile(UserUI userUI) {

    }
}
