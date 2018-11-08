package com.explore.features.user.data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.explore.base.ExploreApplication;
import com.explore.base.ExploreDatabase;
import com.explore.base.PresenterObserver;
import com.explore.features.user.domain.UserDomain;
import com.explore.features.user.domain.UserIteractor;
import com.explore.rest.RestClient;
import com.explore.rest.responses.UserResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserIteractorImpl implements UserIteractor {
    private ObservableUserList observableUserList = new ObservableUserList();
    private List<UserDomain> userDomainList = new ArrayList<>();

    @Override
    public void getUsers(OnUserListFinishListener onUserListFinishListener) {
//        List<UserDomain> userDomainList = new ArrayList<>();
//        userDomainList.add(new UserDomain("George12", "George", "Bardis", "email@email.com", "galatsi", 25));
//        userDomainList.add(new UserDomain("Giannhs32", "Giannhs", "Bardis", "email@email.com", "galatsi", 35));
//        userDomainList.add(new UserDomain("Makis324", "Makis", "Bardis", "email@email.com", "galatsi", 15));
//        userDomainList.add(new UserDomain("Stauros345", "Stauros", "Bardis", "email@email.com", "galatsi", 25));
//        userDomainList.add(new UserDomain("Spuros345", "Spuros", "Bardis", "email@email.com", "galatsi", 25));
//        userDomainList.add(new UserDomain("Nikos3", "Nikos", "Bardis", "email@email.com", "galatsi", 25));
//        onUserListFinishListener.onSuccess(userDomainList);
    }

    @Override
    public void getUser(PresenterObserver presenterObserver, final String userName, final String passWord, final Context context) {
        final UserDao userDao = ExploreDatabase.getDatabase(context).userDao();

        observableUserList.setUserDomainList(userDomainList);
        observableUserList.addObserver(presenterObserver);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                UserDomain userDomain = userDao.findByUsername(userName);
                userDomainList.add(userDomain);
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

//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                final UserDao userDao = ExploreDatabase.getDatabase(context).userDao();
//                UserDomain userDomain = userDao.findByUsername(userName);
//                if (userDomain == null) {
//                    Call<UserResponse> call = RestClient.call().login(new UserDomain(userName, passWord));
//                    call.enqueue(new Callback<UserResponse>() {
//                        @Override
//                        public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
//                            UserResponse userResponse = response.body();
//                            if (userResponse != null) {
//                                final UserDomain userDomain = new UserDomain(userResponse.getId(), Objects.requireNonNull(userResponse).getUsername(),
//                                        userResponse.getFirstName(), userResponse.getLastName(),
//                                        userResponse.getEmail(), userResponse.getAddress(),
//                                        userResponse.getAge(),
//                                        true);
//
//                                AsyncTask.execute(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        userDao.insertUser(userDomain);
//
//                                    }
//                                });
//                                ExploreApplication.setCurrentUser(userDomain);
//                                onUserFinishListener.onSuccess(userDomain);
//                            } else {
//                                onUserFinishListener.onFailure();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
//
//                        }
//                    });
//                } else {
//                    ExploreApplication.setCurrentUser(userDomain);
//                    onUserFinishListener.onSuccess(userDomain);
//                }
//            }
//        });

    }

    @Override
    public void findLoggedInUser() {

    }
}
