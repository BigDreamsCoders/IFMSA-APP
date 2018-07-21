package com.pedrodeveloper14.ifmsaelsalvador.fragments.register_fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.utils.SignUpData;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalFragment extends Fragment implements BlockingStep {

    @BindView(R.id.text_input_edit_text_first_name)
    TextInputEditText firstName;
    @BindView(R.id.text_input_edit_text_last_name)
    TextInputEditText lastName;
    @BindView(R.id.text_input_edit_text_email)
    TextInputEditText email;

    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(() -> onNext(callback), 500);
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        String fName = firstName.getText().toString(),
                lName = lastName.getText().toString(),
                mail = email.getText().toString();
        if (TextUtils.isEmpty(fName) || TextUtils.isEmpty(lName) || TextUtils.isEmpty(mail)) {
            return new VerificationError(getString(R.string.empty_fields_message));
        }
        if (!validateEmail(mail)) {
            email.setError(getString(R.string.email_field_message));
            return new VerificationError("");
        }
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {
        if (!TextUtils.isEmpty(error.getErrorMessage())) {
            Toast.makeText(context, error.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param email email to ve verified
     * @return boolean, true if the email is valid, false otherwise
     */
    private boolean validateEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }


    /**
     * Method that save data from this fragment
     */
    private void onNext(StepperLayout.OnNextClickedCallback callback) {
        String fName = firstName.getText().toString(),
                lName = lastName.getText().toString(),
                mail = email.getText().toString();
        SignUpData.getInstance().
                setFirstStepInfo(fName + " " + lName, mail);
        callback.goToNextStep();
    }
}
