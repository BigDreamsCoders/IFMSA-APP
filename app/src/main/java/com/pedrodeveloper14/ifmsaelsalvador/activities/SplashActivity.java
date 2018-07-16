package com.pedrodeveloper14.ifmsaelsalvador.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pedrodeveloper14.ifmsaelsalvador.R;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(this::startActivity, SPLASH_DURATION);
    }

    /**
     * Method that starts an activity, depending if there is an token or not
    */
    private void startActivity(){
        Intent intent;
        if(getLocalToken().equals("")){
            intent=new Intent();
        }else{
            intent=new Intent(SplashActivity.this, MainActivity.class);
        }
        startActivity(intent);
    }

    /**
     * @return returns the currently saved token
     *  Method that gets a local token
    */
    private String getLocalToken(){
        SharedPreferences preferences=
                getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        return preferences.getString(getString(R.string.shared_preferences_key_token), "");
    }
}
