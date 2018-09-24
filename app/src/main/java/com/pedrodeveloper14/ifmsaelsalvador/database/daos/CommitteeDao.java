package com.pedrodeveloper14.ifmsaelsalvador.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.pedrodeveloper14.ifmsaelsalvador.database.models.Committee;

import java.util.List;

@Dao
public interface CommitteeDao {

    /**
     * Method tha insert an committee to the db
     *
     * @param committee committee to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCommittee(Committee committee);

    /**
     * Method that select all committees
     *
     * @return all committees located in the db
     */
    @Query("SELECT*FROM Committee")
    LiveData<List<Committee>> getAllCommittees();

    /**
     * Method that select an committee by given param
     *
     * @param name name of the committee to search
     * @return committee that match the given param
     */
    @Query("SELECT*FROM Committee WHERE name=:name")
    LiveData<Committee> getCommitteeByName(String name);

    /**
     * Method to delete the committee table
     */
    @Query("DELETE FROM Committee")
    void nuke();

}
