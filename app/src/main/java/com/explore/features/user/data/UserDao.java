package com.explore.features.user.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.explore.features.user.domain.UserDomain;

import java.util.List;

@Dao
public abstract class UserDao {

    @Insert
    abstract void insertUser(UserDomain userDomain);

    @Insert
    abstract void insertUsers(List<UserDomain> userDomainList);

    @Query("SELECT * FROM users")
    abstract List<UserDomain> getAllUsers();

    @Query("SELECT * FROM users WHERE username = :username")
    abstract List<UserDomain> findByUsername(String username);

    @Query("SELECT * FROM users WHERE loggedIn")
    abstract UserDomain findLoggedInUser();
}
