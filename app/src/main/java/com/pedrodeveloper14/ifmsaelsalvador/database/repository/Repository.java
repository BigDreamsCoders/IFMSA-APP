package com.pedrodeveloper14.ifmsaelsalvador.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pedrodeveloper14.ifmsaelsalvador.database.AppDB;
import com.pedrodeveloper14.ifmsaelsalvador.database.daos.CommitteeDao;
import com.pedrodeveloper14.ifmsaelsalvador.database.daos.ProjectDao;
import com.pedrodeveloper14.ifmsaelsalvador.database.daos.UserDao;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Committee;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Project;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.User;

import java.util.List;

public class Repository {

    private ProjectDao mProjectDao;
    private CommitteeDao mCommitteeDao;
    private UserDao mUserDao;

    public Repository(Application application) {
        AppDB db = AppDB.getInstance(application);
        this.mProjectDao = db.projectDao();
        this.mCommitteeDao = db.committeeDao();
        this.mUserDao = db.userDao();
    }

    //Projects things

    public void insertProject(Project project) {
        mProjectDao.insertProject(project);
    }

    public void updateProject(String id, int take_apart) {
        mProjectDao.updateProject(id, take_apart);
    }

    public LiveData<List<Project>> getAllProjects() {
        return mProjectDao.getAllProjects();
    }

    public LiveData<List<Project>> getProjectsByCommittee(String committee) {
        return mProjectDao.getProjectsByCommittee(committee);
    }

    public LiveData<List<Project>> getCurrentUserProjects() {
        return mProjectDao.getCurrentUserProjects();
    }

    public void deleteProjects() {
        mProjectDao.nuke();
    }

    //Committees things

    public void insertCommittee(Committee committee) {
        mCommitteeDao.insertCommittee(committee);
    }

    public LiveData<List<Committee>> getAllCommittees() {
        return mCommitteeDao.getAllCommittees();
    }

    public void deleteCommittees() {
        mCommitteeDao.nuke();
    }

    //User things
    public void insertUser(User user) {
        mUserDao.insertUser(user);
    }

    public LiveData<User> getCurrentUser() {
        return mUserDao.getCurrentUser();
    }

    public void deleteUser() {
        mUserDao.nuke();
    }
}
