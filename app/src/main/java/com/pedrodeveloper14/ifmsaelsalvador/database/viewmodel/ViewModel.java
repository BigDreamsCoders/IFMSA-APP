package com.pedrodeveloper14.ifmsaelsalvador.database.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.pedrodeveloper14.ifmsaelsalvador.database.models.Project;
import com.pedrodeveloper14.ifmsaelsalvador.database.repository.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel{

    private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }

    //Project things
    public void insertProject(Project project){
        new InsertProject(repository).execute(project);
    }
    public static class InsertProject extends AsyncTask<Project, Void, Void>{

        private Repository repository;

        public InsertProject(Repository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(Project... projects) {
            repository.insertProject(projects[0]);
            return null;
        }
    }

    public void updateProject(String id, int take_part){
        new UpdateProject(repository, take_part, id).execute();
    }
    public static class UpdateProject extends AsyncTask<Void, Void, Void>{
        private Repository repository;
        private int take_apart;
        private String id;

        public UpdateProject(Repository repository, int take_apart, String id) {
            this.repository = repository;
            this.take_apart = take_apart==0?1:0;
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.update(id, take_apart);
            return null;
        }
    }

    public LiveData<List<Project>> getAllProjects(){
        return repository.getAllProjects();
    }

    public LiveData<List<Project>> getProjectsByCommittee(String committee){
        return repository.getProjectsByCommittee(committee);
    }

    public void deleteProjectTable(){
        new DeleteProjectTable(repository).execute();
    }
    public static class DeleteProjectTable extends AsyncTask<Void, Void, Void>{

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
}
