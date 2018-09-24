package com.pedrodeveloper14.ifmsaelsalvador.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectPhotosAdapter extends RecyclerView.Adapter<ProjectPhotosAdapter.ViewHolder> {

    List<String> photos;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cardview_project_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url=photos.get(position);
        ImageLoader.loadImage(url, holder.imageViewProjectPhoto);
    }

    @Override
    public int getItemCount() {
        return photos == null ? 0 : photos.size();
    }

    public void setList(List<String> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_project_photo_cardview)
        ImageView imageViewProjectPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
