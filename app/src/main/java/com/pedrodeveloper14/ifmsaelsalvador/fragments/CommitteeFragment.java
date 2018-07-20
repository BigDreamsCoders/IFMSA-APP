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
import com.pedrodeveloper14.ifmsaelsalvador.activities.CommitteeViewActivity;
import com.pedrodeveloper14.ifmsaelsalvador.adapters.CommitteeAdapter;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Committee;
import com.pedrodeveloper14.ifmsaelsalvador.database.viewmodel.ViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CommitteeFragment extends Fragment {

    @BindView(R.id.recycler_view_generic)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private CommitteeAdapter adapter;
    private Context context;
    private ViewModel viewModel;

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
        setThings();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setThings() {
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        adapter = new CommitteeAdapter() {
            @Override
            public void onCardClick(Committee committee) {
                Intent intent = new Intent(context, CommitteeViewActivity.class);
                intent.putExtra("name", committee.getName());
                intent.putExtra("description", committee.getDescription());
                intent.putExtra("url", committee.getImageUrl());
                startActivity(intent);
            }
        };
        viewModel.getAllCommittees().observe(this, list -> adapter.setCommittees(list));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}
