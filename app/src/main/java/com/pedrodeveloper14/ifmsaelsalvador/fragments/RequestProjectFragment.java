package com.pedrodeveloper14.ifmsaelsalvador.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Committee;
import com.pedrodeveloper14.ifmsaelsalvador.database.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RequestProjectFragment extends Fragment {

    @BindView(R.id.edit_text_project_name)
    EditText editTextProjectName;
    @BindView(R.id.edit_text_project_place)
    EditText editTextProjectPlace;
    @BindView(R.id.edit_text_vacancies)
    EditText editTextProjectVacancies;
    @BindView(R.id.time_picker_project_time)
    TimePicker timePickerProjectTime;
    @BindView(R.id.date_picker_project_date)
    DatePicker datePickerProjectDate;
    @BindView(R.id.spinner_committee)
    Spinner spinnerCommittee;
    @BindView(R.id.image_button_project_photo)
    ImageButton imageButtonProjectPhoto;
    @BindView(R.id.button_request_project)
    Button buttonRequestProject;

    private Unbinder unbinder;
    private ArrayAdapter<String> adapter;
    private ViewModel viewModel;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_project, container, false);
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
        viewModel.getAllCommittees().observe(this, list ->{
                adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getCommitteesData(list));
                adapter.notifyDataSetChanged();
                spinnerCommittee.setAdapter(adapter);
        });
        spinnerCommittee.setAdapter(adapter);
        datePickerProjectDate.init(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, null);
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            timePickerProjectTime.setCurrentHour(12);
            timePickerProjectTime.setCurrentMinute(0);
        }else{
            timePickerProjectTime.setHour(12);
            timePickerProjectTime.setMinute(0);
        }
    }

    private String[] getCommitteesData(List<Committee> committees) {
        if (committees != null && committees.size() != 0) {
            int size = committees.size();
            List<String> aux = new ArrayList<>();
            for (Committee committee : committees) {
                aux.add(committee.getName());
            }
            return Arrays.copyOf(aux.toArray(), size, String[].class);
        } else {
            String[] data = {"Default"};
            return data;
        }
    }

    private void setListeners(){
        /*imageButtonProjectPhoto.setOnClickListener(v->{
            ImagePicker.pickImage((Activity)context);
        });*/
        buttonRequestProject.setOnClickListener(v->{
            if(verifyFields()){
                requestProject();
                Toast.makeText(context, "Yea wey", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean verifyFields(){
        String name=editTextProjectName.getText().toString();
        String place=editTextProjectPlace.getText().toString();
        String vacancies=editTextProjectVacancies.getText().toString();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(place)||TextUtils.isEmpty(vacancies)){
            if(TextUtils.isEmpty(name)){
                editTextProjectName.setError(getString(R.string.empty_fields_message));
            }else if (TextUtils.isEmpty(place)){
                editTextProjectPlace.setError(getString(R.string.empty_fields_message));
            }else{
                editTextProjectVacancies.setError(getString(R.string.empty_fields_message));
            }
            return false;
        }
        return true;
    }

    private void requestProject(){
        String name=editTextProjectName.getText().toString();
        String place=editTextProjectPlace.getText().toString();
        String vacancies=editTextProjectVacancies.getText().toString();
        String day=String.valueOf(datePickerProjectDate.getDayOfMonth());
        String month=String.valueOf(datePickerProjectDate.getMonth());
        String year=String.valueOf(datePickerProjectDate.getYear());
        String hour, minute;
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            hour=String.valueOf(timePickerProjectTime.getCurrentHour());
            minute=String.valueOf(timePickerProjectTime.getCurrentMinute());
        }else{
            hour=String.valueOf(timePickerProjectTime.getHour());
            minute=String.valueOf(timePickerProjectTime.getMinute());
        }
        System.out.println(name+"\n"+place+"\n"+vacancies+"\n"+day+"\n"+month+"\n"+year+"\n"+hour+"\n"+minute);
    }
}