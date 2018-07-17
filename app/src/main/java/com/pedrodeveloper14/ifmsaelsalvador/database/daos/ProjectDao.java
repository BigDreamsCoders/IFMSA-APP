package com.pedrodeveloper14.ifmsaelsalvador.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.pedrodeveloper14.ifmsaelsalvador.database.models.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    /**
     * Method tha insert an project to the db
     * @param project project to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(Project project);

    /**
     * Method to updateProject the state of assistance
     * @param id id to be searched
     */
    @Query("UPDATE Project SET took_part=:take_apart WHERE id= :id")
    void updateProject(String id, int take_apart);

    /**
     * Method that select all projects
     * @return all projects located in the db
     */
    @Query("SELECT*FROM Project")
    LiveData<List<Project>> getAllProjects();

    /**
     * @param committee committee nameto be searched
     * @return all projects that match the given committee name
     */
    @Query("SELECT*FROM Project WHERE committee LIKE :committee")
    LiveData<List<Project>> getProjectsByCommittee(String committee);

    /**
     * Method to delete the project table
     */
    @Query("DELETE FROM Project")
    void nuke();
}
