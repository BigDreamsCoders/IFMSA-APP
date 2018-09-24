package com.pedrodeveloper14.ifmsaelsalvador.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.adapters.ProjectPhotosAdapter;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Project;
import com.pedrodeveloper14.ifmsaelsalvador.database.viewmodel.ViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectViewActivity extends AppCompatActivity {

    @BindView(R.id.image_view_project_committee)
    ImageView imageViewProjectCommittee;
    @BindView(R.id.text_view_project_name)
    TextView textViewProjectName;
    @BindView(R.id.text_view_project_date)
    TextView textViewProjectDate;
    @BindView(R.id.text_view_project_hour)
    TextView textViewProjectHour;
    @BindView(R.id.text_view_project_description)
    TextView textViewProjectDescription;
    @BindView(R.id.button_take_part)
    Button buttonTakePart;
    @BindView(R.id.recycler_view_project_photos)
    RecyclerView recyclerViewProjectPhotos;

    private String projectID;
    private ViewModel viewModel;
    private Project project;
    private ProjectPhotosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);
        ButterKnife.bind(this);
        getDataFromIntent(getIntent());
        setThings();
    }

    /**
     * Method that get the extra data from the receiving intent
     *
     * @param intent intent to get data
     */
    private void getDataFromIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            textViewProjectName.setText(bundle.getString("name"));
            textViewProjectHour.setText(bundle.getString("hour"));
            textViewProjectDate.setText(bundle.getString("date"));
            textViewProjectDescription.setText(bundle.getString("description"));

            adapter = new ProjectPhotosAdapter();
            adapter.setList(bundle.getStringArrayList("photos"));
        }
    }

    /**
     * Method that set references
     */
    private void setThings() {
        recyclerViewProjectPhotos.setAdapter(adapter);
        recyclerViewProjectPhotos.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Method that set listeners
     */
    private void setListeners() {
        buttonTakePart.setOnClickListener(v -> {
            viewModel.updateProject(project.getId(), project.getTook_part());
            buttonTakePart
                    .setText(project.getTook_part() == 1 ? R.string.take_part_button : R.string.dismiss_button);
        });
    }
}
