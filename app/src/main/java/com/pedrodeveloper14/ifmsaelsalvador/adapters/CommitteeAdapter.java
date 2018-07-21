package com.pedrodeveloper14.ifmsaelsalvador.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Committee;
import com.pedrodeveloper14.ifmsaelsalvador.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class CommitteeAdapter extends RecyclerView.Adapter<CommitteeAdapter.ViewHolder> {

    private List<Committee> committees;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cardview_committee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Committee aux = committees.get(position);
        holder.committee = aux;
        holder.textViewCommitteeName.setText(aux.getName());
        holder.textViewCommitteeDescription.setText(aux.getDescription());
        ImageLoader.loadImage(aux.getImageUrl(), holder.imageViewCommitteeLogo);
    }

    @Override
    public int getItemCount() {
        return committees == null ? 0 : committees.size();
    }

    /**
     * Method to set list of committees
     *
     * @param committees list of committees in the database
     */
    public void setCommittees(List<Committee> committees) {
        this.committees = committees;
        notifyDataSetChanged();
    }

    /**
     * Abstract method that set the action when the {@link android.support.v7.widget.CardView} is open
     *
     * @param committee committee selected
     */
    public abstract void onCardClick(Committee committee);

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_committee_logo)
        ImageView imageViewCommitteeLogo;
        @BindView(R.id.text_view_committee_description)
        TextView textViewCommitteeDescription;
        @BindView(R.id.text_view_committee_name)
        TextView textViewCommitteeName;
        private Committee committee;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> onCardClick(committee));
        }
    }
}