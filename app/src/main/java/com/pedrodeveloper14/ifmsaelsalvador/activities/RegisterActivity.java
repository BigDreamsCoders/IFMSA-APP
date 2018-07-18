package com.pedrodeveloper14.ifmsaelsalvador.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.adapters.StepperAdapter;
import com.stepstone.stepper.StepperLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity{

    @BindView(R.id.stepper_layout)
    StepperLayout stepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        stepperLayout.setAdapter(new StepperAdapter(getSupportFragmentManager(), this));
    }
}
