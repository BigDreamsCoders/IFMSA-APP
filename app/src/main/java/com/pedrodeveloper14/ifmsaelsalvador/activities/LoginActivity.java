package com.pedrodeveloper14.ifmsaelsalvador.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    private void setThings(){
        buttonLogin.setOnClickListener(v->verifyLogin());
    }



    private void verifyLogin(){
        String user=editTextUsername.getText().toString();
        String pass=editTextPassword.getText().toString();
        linearLayoutFields.setVisibility(View.GONE);
        linearLayoutProgress.setVisibility(View.VISIBLE);
        if(user.equals("")||pass.equals("")){
            linearLayoutFields.setVisibility(View.VISIBLE);
            linearLayoutProgress.setVisibility(View.GONE);
            Snackbar.make(relativeLayoutMain, R.string.empty_fields_message, Snackbar.LENGTH_LONG);
        }else{
        }
    }
}
