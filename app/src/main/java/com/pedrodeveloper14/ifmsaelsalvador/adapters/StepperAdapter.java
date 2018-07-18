package com.pedrodeveloper14.ifmsaelsalvador.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.pedrodeveloper14.ifmsaelsalvador.fragments.register_fragments.PersonalFragment;
import com.pedrodeveloper14.ifmsaelsalvador.fragments.register_fragments.UserFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.adapter.AbstractStepAdapter;

public class StepperAdapter extends AbstractFragmentStepAdapter {

    private static final String KEY = "CURRENT_STEP_POSITION_KEY";

    public StepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        Bundle bundle=new Bundle();
        Step fragment;
        switch (position){
            case 0:
                fragment= new PersonalFragment();
                bundle.putInt(KEY, position);
                ((Fragment)fragment).setArguments(bundle);
                break;
            default:
                fragment=new UserFragment();
                bundle.putInt(KEY, position);
                ((Fragment)fragment).setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}