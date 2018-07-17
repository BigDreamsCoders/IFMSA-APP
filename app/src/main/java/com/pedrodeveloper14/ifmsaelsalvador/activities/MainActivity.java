package com.pedrodeveloper14.ifmsaelsalvador.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.fragments.ProfileFragment;
import com.pedrodeveloper14.ifmsaelsalvador.fragments.ProjectsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation_view_main_activity)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setThings();
        if (savedInstanceState == null) {
            setFragment(new ProjectsFragment(), getString(R.string.profile_menu));
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_manu, menu);
        return true;
    }

    private void setThings() {
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemID = item.getItemId();
            String title = "";
            Fragment fragment;
            switch (itemID) {
                case R.id.projects_menu:
                    fragment = new ProjectsFragment();
                    title = ((ProjectsFragment) fragment).getTitle();
                    break;
                default: /*R.id.profile_menu:*/
                    fragment=new ProfileFragment();
                    title=((ProfileFragment)fragment).getTitle();
            }
            setFragment(fragment, title);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void setFragment(Fragment fragment, String title) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.main_fragment_container, fragment).commit();
        getSupportActionBar().setTitle(title);
    }

    private String getToken(){
        return getSharedPreferences(getPackageName(), Context.MODE_PRIVATE)
                .getString(getString(R.string.shared_preferences_key_token), "");
    }

}
