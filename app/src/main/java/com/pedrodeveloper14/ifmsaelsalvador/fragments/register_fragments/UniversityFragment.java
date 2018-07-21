package com.pedrodeveloper14.ifmsaelsalvador.fragments.register_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.activities.CompleteFormActivity;
import com.pedrodeveloper14.ifmsaelsalvador.utils.SignUpData;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UniversityFragment extends Fragment implements BlockingStep {

    @BindView(R.id.spinner_university)
    Spinner spinnerUniversity;
    @BindView(R.id.spinner_year)
    Spinner spinnerYear;
    @BindView(R.id.text_input_edit_text_phone_number)
    TextInputEditText textInputPhone;

    private ArrayAdapter<CharSequence> universityAdapter, yearAdapter;

    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_university, container, false);
        ButterKnife.bind(this, view);
        setThings();
        return view;
    }

    /**
     * Method that ser references
     */
    private void setThings() {
        universityAdapter = ArrayAdapter.createFromResource(context, R.array.colleges_array, android.R.layout.simple_spinner_item);
        yearAdapter = ArrayAdapter.createFromResource(context, R.array.years_array, android.R.layout.simple_spinner_item);
        spinnerUniversity.setAdapter(universityAdapter);
        spinnerYear.setAdapter(yearAdapter);
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        SignUpData.getInstance().setThirdStepInfo(spinnerUniversity.getSelectedItem().toString(), spinnerYear.getSelectedItem().toString(), textInputPhone.getText().toString());
        /*TODO fix this, add API call*/
        context.startActivity(new Intent(context, CompleteFormActivity.class));
        ((Activity) context).finish();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        String phone = textInputPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            textInputPhone.setError(getString(R.string.empty_fields_message));
            textInputPhone.requestFocus();
            return new VerificationError("");
        }

        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
