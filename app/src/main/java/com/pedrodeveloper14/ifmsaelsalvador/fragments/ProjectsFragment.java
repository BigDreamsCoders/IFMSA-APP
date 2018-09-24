package com.pedrodeveloper14.ifmsaelsalvador.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.activities.ProjectViewActivity;
import com.pedrodeveloper14.ifmsaelsalvador.activities.SplashActivity;
import com.pedrodeveloper14.ifmsaelsalvador.adapters.ProjectsAdapter;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Project;
import com.pedrodeveloper14.ifmsaelsalvador.database.viewmodel.ViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectsFragment extends Fragment {

    @BindView(R.id.recycler_view_generic)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private ProjectsAdapter adapter;
    private Context context;
    private ViewModel viewModel;
    private Project project;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generic_recycler_view, container, false);
        unbinder = ButterKnife.bind(this, view);
        bindView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * Method to set references and an listeners
     */
    private void bindView() {
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        adapter = new ProjectsAdapter() {
            @Override
            public void onCardViewClick(Project project) {
                ProjectsFragment.this.project=project;
                viewModel
                        .getCommitteeByName(project.getCommittee())
                        .observe(ProjectsFragment.this, committee -> {
                            ProjectsFragment.this.startActivity(ProjectsFragment.this.project, committee.getImageUrl());
                        });
            }

            @Override
            public void onButtonClick(String id, int take_part) {
                viewModel.updateProject(id, take_part);
                notifyDataSetChanged();
            }
        };
        viewModel.getAllProjects()
                .observe(this, list -> adapter.setProjects(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }


    /**
     * Method that starts activity
     *
     * @param project project to be show in the activity
     */
    private void startActivity(Project project, String committeeUrl) {
        Intent intent = new Intent(context, ProjectViewActivity.class);
        intent.putExtra("name", project.getName());
        intent.putExtra("date", project.getDate());
        intent.putExtra("hour", project.getHour());
        intent.putExtra("description", project.getDescription());
        intent.putExtra("committee", committeeUrl);
        intent.putStringArrayListExtra("photos", new ArrayList<>(project.getPhotos()));
        startActivity(intent);
    }
}
