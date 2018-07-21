package com.pedrodeveloper14.ifmsaelsalvador.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pedrodeveloper14.ifmsaelsalvador.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username_login)
    EditText editTextUsername;
    @BindView(R.id.password_login)
    EditText editTextPassword;
    @BindView(R.id.button_login)
    Button buttonLogin;
    @BindView(R.id.forgot_pass_login)
    TextView textViewForgot;
    @BindView(R.id.register_login)
    TextView textViewRegister;
    @BindView(R.id.main_layout_login)
    RelativeLayout relativeLayoutMain;
    @BindView(R.id.linear_layout_fields)
    LinearLayout linearLayoutFields;
    @BindView(R.id.login_progress)
    LinearLayout linearLayoutProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(null);
            getWindow().setAllowEnterTransitionOverlap(true);
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setThings();
    }


    /**
     * Method that set listeners
     */
    private void setThings() {
        buttonLogin.setOnClickListener(v -> verifyLogin());
        textViewRegister.setOnClickListener(v -> startActivity(v.getId()));
        textViewForgot.setOnClickListener(v -> startActivity(v.getId()));
    }

    /**
     * Method that verify if the fields are empty, if there are, an error message is triggered
     */
    private void verifyLogin() {
        String user = editTextUsername.getText().toString();
        String pass = editTextPassword.getText().toString();
        EditText view;
        if (user.equals("") || pass.equals("")) {
            if (user.equals("") && pass.equals("")) {
                editTextUsername.setError(getString(R.string.empty_fields_message));
                editTextPassword.setError(getString(R.string.empty_fields_message));
            } else {
                view = user.equals("") ? editTextUsername : editTextPassword;
                view.setError(getString(R.string.empty_fields_message));
            }
        } else {
            linearLayoutFields.setVisibility(View.GONE);
            linearLayoutProgress.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() ->
                    startActivity(buttonLogin.getId()), 2000);
        }
    }

    private void startActivity(int id) {
        Intent intent;
        switch (id) {
            case R.id.register_login:
                intent = new Intent(this, RegisterActivity.class);
                break;
            default:/*case R.id.button_login:*/
                intent = new Intent(this, MainActivity.class);
                break;/*
            default:
                intent=new Intent(this, MainActivity.class);
                break;*/
        }
        startActivity(intent);
        finish();
    }
}
