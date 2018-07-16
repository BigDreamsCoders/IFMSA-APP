package com.pedrodeveloper14.ifmsaelsalvador.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pedrodeveloper14.ifmsaelsalvador.database.AppDB;
import com.pedrodeveloper14.ifmsaelsalvador.database.daos.ProjectDao;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Project;

import java.util.List;

public class Repository {

    private ProjectDao mProjectDao;

    public Repository(Application application) {
        AppDB db=AppDB.getInstance(application);
        this.mProjectDao = db.projectDao();
    }

    //Projects things

    public void insertProject(Project project){
        mProjectDao.insertProject(project);
    }
    public void update(String id, int take_apart){
        mProjectDao.updateProject(id, take_apart);
    }
    public LiveData<List<Project>> getAllProjects(){
        return mProjectDao.getAllProjects();
    }
    public LiveData<List<Project>> getProjectsByCommittee(String committee){
        return mProjectDao.getProjectsByCommittee(committee);
    }
    public void deleteProjects(){
        mProjectDao.nuke();
    }
}
