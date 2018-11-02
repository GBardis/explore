package com.explore.features.user.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.explore.features.user.domain.UserDomain;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(UserDomain userDomain);

    @Insert
    void insertUsers(List<UserDomain> userDomainList);

    @Query("SELECT * FROM users")
    List<UserDomain> getAllUsers();

    @Query("SELECT * FROM users WHERE username = :username")
    UserDomain findByUsername(String username);
}
