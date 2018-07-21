package com.pedrodeveloper14.ifmsaelsalvador.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.pedrodeveloper14.ifmsaelsalvador.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompleteFormActivity extends AppCompatActivity {

    @BindView(R.id.button_go_to_login)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_form);
        ButterKnife.bind(this);
        setThings();
    }

    /**
     * Method that set references and listeners
     */
    private void setThings() {
        button.setOnClickListener(v ->
                startActivity(new Intent(CompleteFormActivity.this, LoginActivity.class))
        );
    }
}
