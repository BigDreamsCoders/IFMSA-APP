package com.pedrodeveloper14.ifmsaelsalvador.database.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.pedrodeveloper14.ifmsaelsalvador.database.models.Committee;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Project;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.User;
import com.pedrodeveloper14.ifmsaelsalvador.database.repository.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    //Project things
    public void insertProject(Project project) {
        new InsertProject(repository, project).execute();
    }

    public static class InsertProject extends AsyncTask<Void, Void, Void> {

        private Repository repository;
        private Project project;

        public InsertProject(Repository repository, Project project) {
            this.repository = repository;
            this.project = project;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.insertProject(project);
            return null;
        }
    }

    public void updateProject(String id, int take_part) {
        new UpdateProject(repository, take_part, id).execute();
    }

    public static class UpdateProject extends AsyncTask<Void, Void, Void> {
        private Repository repository;
        private int take_apart;
        private String id;

        public UpdateProject(Repository repository, int take_apart, String id) {
            this.repository = repository;
            this.take_apart = take_apart == 0 ? 1 : 0;
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.updateProject(id, take_apart);
            return null;
        }
    }

    public LiveData<List<Project>> getAllProjects() {
        return repository.getAllProjects();
    }

    public LiveData<List<Project>> getProjectsByCommittee(String committee) {
        return repository.getProjectsByCommittee(committee);
    }

    public LiveData<List<Project>> getCurrentUserProjects() {
        return repository.getCurrentUserProjects();
    }

    public void deleteProjectTable() {
        new DeleteProjectTable(repository).execute();
    }

    public static class DeleteProjectTable extends AsyncTask<Void, Void, Void> {

        private Repository repository;

        public DeleteProjectTable(Repository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.deleteProjects();
            return null;
        }
    }

    //Committees things
    public void insertCommittee(Committee committee) {
        new InsertCommittee(repository, committee).execute();
    }

    public static class InsertCommittee extends AsyncTask<Void, Void, Void> {
        private Repository repository;
        private Committee committee;

        public InsertCommittee(Repository repository, Committee committee) {
            this.repository = repository;
            this.committee = committee;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.insertCommittee(committee);
            return null;
        }
    }

    public LiveData<List<Committee>> getAllCommittees() {
        return repository.getAllCommittees();
    }

    public void deleteCommitteeTable() {
        new DeleteProjectTable(repository).execute();
    }

    public static class DeleteCommitteeTable extends AsyncTask<Void, Void, Void> {

        private Repository repository;

        public DeleteCommitteeTable(Repository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.deleteCommittees();
            return null;
        }
    }

    //User things
    public void insertUser(User user) {
        new InsertUser(repository, user).execute();
    }

    public static class InsertUser extends AsyncTask<Void, Void, Void> {

        private Repository repository;
        private User user;

        public InsertUser(Repository repository, User user) {
            this.repository = repository;
            this.user = user;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.insertUser(user);
            return null;
        }
    }

    public LiveData<User> getCurrentUser() {
        return repository.getCurrentUser();
    }

    public void deleteUser() {
        new DeleteUserTable(repository).execute();
    }

    public static class DeleteUserTable extends AsyncTask<Void, Void, Void> {

        private Repository repository;

        public DeleteUserTable(Repository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.deleteUser();
            return null;
        }
    }
}
