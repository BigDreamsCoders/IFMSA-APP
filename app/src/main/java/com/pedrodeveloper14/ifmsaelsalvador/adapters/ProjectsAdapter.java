package com.pedrodeveloper14.ifmsaelsalvador.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Project;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder> {

    private List<Project> projects;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_projects, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Project aux=projects.get(position);
        holder.place.setText(aux.getPlace());
        holder.date.setText(aux.getDate());
        holder.hour.setText(aux.getHour());
        holder.name.setText(aux.getName());
    }

    @Override
    public int getItemCount() {
        return projects==null?0:projects.size();
    }

    /**
     * @param projects projects list that will be show
     */
    public void setProjects(List<Project> projects){
        this.projects=projects;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_date_cardview) TextView date;
        @BindView(R.id.text_view_hour_cardview) TextView hour;
        @BindView(R.id.text_view_place_cardview) TextView place;
        @BindView(R.id.text_view_project_name_cardview) TextView name;
        @BindView(R.id.image_view_project_photo_cardview) ImageView photo;
        @BindView(R.id.button_take_part_cardview) Button takePart;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view->onCardViewClick());
            takePart.setOnClickListener(view->onButtonClick());
        }
    }


    /**
     * method to set onclick listener to the whole {@link android.support.v7.widget.CardView}
     */
    public abstract void onCardViewClick();

    /**
     * method to set onclick listener to the {@link Button} in the {@link android.support.v7.widget.CardView}
     */
    public abstract void onButtonClick();
}
