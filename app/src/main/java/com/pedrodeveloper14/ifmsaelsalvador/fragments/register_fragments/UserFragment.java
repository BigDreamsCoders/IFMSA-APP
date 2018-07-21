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

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.utils.SignUpData;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserFragment extends Fragment implements BlockingStep {

    @BindView(R.id.text_input_edit_text_username)
    TextInputEditText username;
    @BindView(R.id.text_input_edit_text_password)
    TextInputEditText pass1;
    @BindView(R.id.text_input_edit_text_password2)
    TextInputEditText pass2;

    private Context context;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        callback.goToPrevStep();
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        String user = username.getText().toString(),
                password1 = pass1.getText().toString(),
                password2 = pass2.getText().toString();
        if (TextUtils.isEmpty(user) ||
                TextUtils.isEmpty(password1) ||
                TextUtils.isEmpty(password2)) {
            return new VerificationError(getString(R.string.empty_fields_message));
        }
        if (!validatePassword(password1)) {
            pass1.setError(getString(R.string.email_field_message));
            pass1.requestFocus();
            return new VerificationError("");
        }
        if (!TextUtils.equals(password1, password2)) {
            pass2.setError(getString(R.string.password_match_message));
            pass2.requestFocus();
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

    /**
     * Method to validate password
     *
     * @param pass password to be validated
     * @return true if the password is valid, false otherwise
     */
    private boolean validatePassword(String pass) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(pass).matches();
    }

    /**
     * Method that save data from this fragment
     */
    private void onNext(StepperLayout.OnNextClickedCallback callback) {
        SignUpData.getInstance().
                setSecondStepInfo(
                        username.getText().toString(),
                        pass1.getText().toString()
                );
        callback.goToNextStep();
    }
}
