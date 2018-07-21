package com.pedrodeveloper14.ifmsaelsalvador.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.adapters.ProjectsAdapter;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Project;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.User;
import com.pedrodeveloper14.ifmsaelsalvador.database.viewmodel.ViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProfileFragment extends Fragment {

    @BindView(R.id.text_view_profile_university)
    TextView university;
    @BindView(R.id.text_view_profile_email)
    TextView email;
    @BindView(R.id.text_view_profile_full_name)
    TextView fullName;
    @BindView(R.id.image_button_profile_picture)
    ImageButton profile_pic;
    @BindView(R.id.recycler_view_profile_projects)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private Context context;
    private ProjectsAdapter adapter;
    private ViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
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
    private void setThings() {
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        adapter = new ProjectsAdapter() {
            @Override
            public void onCardViewClick(Project project) {

            }

            @Override
            public void onButtonClick(String id, int take_part) {
                viewModel.updateProject(id, take_part);
                notifyDataSetChanged();
            }
        };
        viewModel.getCurrentUserProjects()
                .observe(this, list -> adapter.setProjects(list));
        viewModel.getCurrentUser()
                .observe(this, this::setUserInfo);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    /**
     * Method that set the info of the current user
     *
     * @param user current user logged
     */
    private void setUserInfo(User user) {
        fullName.setText(user.getName());
        email.setText(user.getEmail());
        university.setText(user.getCollage());
    }
}
