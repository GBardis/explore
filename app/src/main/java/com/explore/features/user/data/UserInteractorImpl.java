package com.explore.features.user.data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.explore.base.ExploreApplication;
import com.explore.base.ExploreDatabase;
import com.explore.base.PresenterObserver;
import com.explore.features.user.domain.UserDomain;
import com.explore.features.user.domain.UserInteractor;
import com.explore.rest.RestClient;
import com.explore.rest.responses.UserResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInteractorImpl implements UserInteractor {
    private ObservableUserList observableUserList = new ObservableUserList();
    private List<UserDomain> userDomainList = new ArrayList<>();
    private boolean isLoggedInUser = false;


    @Override
    public void getUsers(PresenterObserver presenterObserver, Context context) {
        final UserDao userDao = ExploreDatabase.getDatabase(context).userDao();
        observableUserList.setUserDomainList(userDomainList);
        observableUserList.addObserver(presenterObserver);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userDomainList = userDao.getAllUsers();
                if (userDomainList.size() <= 1) {
                    userDomainList.clear();
                    Call<List<UserResponse>> call = RestClient.call().fetchUsers();
                    call.enqueue(new Callback<List<UserResponse>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<UserResponse>> call, @NonNull Response<List<UserResponse>> response) {
                            List<UserResponse> userResponseList = response.body();
                            for (UserResponse userResponse : userResponseList) {
                                userDomainList.add(new UserDomain(userResponse.getId(),
                                        Objects.requireNonNull(userResponse).getUsername(),
                                        userResponse.getFirstName(), userResponse.getLastName(),
                                        userResponse.getEmail(), userResponse.getAddress(),
                                        userResponse.getAge(),
                                        true));
                            }
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    userDao.updateUsers(userDomainList, ExploreApplication.getCurrentUser().getUserId());
                                }
                            });
                            observableUserList.changeDataset(userDomainList);
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<UserResponse>> call, @NonNull Throwable t) {

                        }
                    });
                } else {
                    observableUserList.changeDataset(userDomainList);
                }
            }
        });
    }

    @Override
    public void getUser(PresenterObserver presenterObserver, final String userName, final String passWord, final Context context) {
        final UserDao userDao = ExploreDatabase.getDatabase(context).userDao();

        observableUserList.setUserDomainList(userDomainList);
        observableUserList.addObserver(presenterObserver);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userDomainList = userDao.findByUsername(userName);

                if (userDomainList.isEmpty()) {
                    Call<UserResponse> call = RestClient.call().login(new UserDomain(userName, passWord));
                    call.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                            UserResponse userResponse = response.body();

                            final UserDomain userDomain = new UserDomain(userResponse.getId(), Objects.requireNonNull(userResponse).getUsername(),
                                    userResponse.getFirstName(), userResponse.getLastName(),
                                    userResponse.getEmail(), userResponse.getAddress(),
                                    userResponse.getAge(),
                                    true);
                            userDomainList.add(userDomain);
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    userDao.insertUser(userDomain);

                                }
                            });
                            ExploreApplication.setCurrentUser(userDomain);
                            observableUserList.changeDataset(userDomainList);
                        }

                        @Override
                        public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {

                        }
                    });
                } else {
                    observableUserList.changeDataset(userDomainList);
                }
            }
        });
    }

    @Override
    public void findLoggedInUser(final Context context, final OnfindLoggedInUserFinishListener onfindLoggedInUserFinishListener) {
        AsyncTask.execute(new Runnable() {
            final UserDao userDao = ExploreDatabase.getDatabase(context).userDao();

            @Override
            public void run() {
                UserDomain userDomain = userDao.findLoggedInUser();
                if (userDomain != null) {
                    ExploreApplication.setCurrentUser(userDomain);
                    onfindLoggedInUserFinishListener.onSuccess(isLoggedInUser);
                }
            }
        });
    }
}
