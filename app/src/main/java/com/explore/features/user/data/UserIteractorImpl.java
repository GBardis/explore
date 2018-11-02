package com.explore.features.user.data;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.explore.features.user.domain.UserDomain;
import com.explore.features.user.domain.UserIteractor;
import com.explore.rest.RestClient;
import com.explore.rest.responses.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserIteractorImpl implements UserIteractor {

    @Override
    public void getUsers(OnUserListFinishListener onUserListFinishListener) {
        List<UserDomain> userDomainList = new ArrayList<>();
        userDomainList.add(new UserDomain("George12", "George", "Bardis", "email@email.com", "galatsi", 25));
        userDomainList.add(new UserDomain("Giannhs32", "Giannhs", "Bardis", "email@email.com", "galatsi", 35));
        userDomainList.add(new UserDomain("Makis324", "Makis", "Bardis", "email@email.com", "galatsi", 15));
        userDomainList.add(new UserDomain("Stauros345", "Stauros", "Bardis", "email@email.com", "galatsi", 25));
        userDomainList.add(new UserDomain("Spuros345", "Spuros", "Bardis", "email@email.com", "galatsi", 25));
        userDomainList.add(new UserDomain("Nikos3", "Nikos", "Bardis", "email@email.com", "galatsi", 25));
        onUserListFinishListener.onSuccess(userDomainList);
    }

    @Override
    public void getUser(final OnUserFinishListener onUserFinishListener, String userEmail) {
//
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Call<UserResponse> call = RestClient.call().login(new UserDomain("teamBlack", "theBlacksw0rd"));
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                        UserResponse userResponse = response.body();
                        UserDomain userDomain = new UserDomain(userResponse.getUsername(),
                                userResponse.getFirstName(), userResponse.getLastName(),
                                userResponse.getEmail(), userResponse.getAddress(),
                                userResponse.getAge());
                        onUserFinishListener.onSuccess(userDomain);
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {

                    }
                });
            }
        });
    }
}
