package com.pedrodeveloper14.ifmsaelsalvador.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.pedrodeveloper14.ifmsaelsalvador.database.models.User;

@Dao
public interface UserDao {

    /**
     * Method tha insert an user to the db
     *
     * @param user user to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    /**
     * Method tha return the current logged user
     *
     * @return current logged user
     */
    @Query("SELECT*FROM User")
    LiveData<User> getCurrentUser();

    /**
     * Method to delete the user table
     */
    @Query("DELETE FROM User")
    void nuke();


}
