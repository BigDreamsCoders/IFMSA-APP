package com.pedrodeveloper14.ifmsaelsalvador.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.adapters.CommitteeAdapter;
import com.pedrodeveloper14.ifmsaelsalvador.adapters.ProjectsAdapter;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Committee;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Project;
import com.pedrodeveloper14.ifmsaelsalvador.database.viewmodel.ViewModel;
import com.pedrodeveloper14.ifmsaelsalvador.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommitteeViewActivity extends AppCompatActivity {

    @BindView(R.id.text_view_committee_name)
    TextView textViewCommitteeName;
    @BindView(R.id.text_view_committee_description)
    TextView textViewCommitteeDescription;
    @BindView(R.id.recycler_view_committee_projects)
    RecyclerView recyclerViewCommitteeProjects;
    @BindView(R.id.image_view_committee_logo)
    ImageView imageViewCommitteeLogo;
    @BindView(R.id.button_join_committee)
    Button buttonJoinCommittee;

    private ProjectsAdapter adapter;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_view);
        ButterKnife.bind(this);
        getDataFromIntent(getIntent());
    }

    private void getDataFromIntent(Intent intent){
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            textViewCommitteeName.setText(bundle.getString("name"));
            textViewCommitteeDescription.setText(bundle.getString("description"));
            ImageLoader.loadImage(bundle.getString("url"), imageViewCommitteeLogo);
        }
    }

    private void setThings(){
        viewModel= ViewModelProviders.of(this).get(ViewModel.class);
        adapter=new ProjectsAdapter() {
            @Override
            public void onCardViewClick(Project project) {

            }

            @Override
            public void onButtonClick(String id, int take_part) {
                viewModel.updateProject(id, take_part);
                notifyDataSetChanged();
            }
        };
        viewModel.getProjectsByCommittee(textViewCommitteeName.getText().toString())
                .observe(this, projects -> adapter.setProjects(projects));
    }

}