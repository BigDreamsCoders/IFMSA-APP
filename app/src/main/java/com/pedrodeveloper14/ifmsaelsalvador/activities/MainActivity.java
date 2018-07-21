package com.pedrodeveloper14.ifmsaelsalvador.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.pedrodeveloper14.ifmsaelsalvador.fragments.CommitteeFragment;
import com.pedrodeveloper14.ifmsaelsalvador.fragments.ProfileFragment;
import com.pedrodeveloper14.ifmsaelsalvador.fragments.ProjectsFragment;
import com.pedrodeveloper14.ifmsaelsalvador.fragments.RequestProjectFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar_fragment_main_content)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setThings();
        if (savedInstanceState == null) {
            setFragment(new ProfileFragment(), getString(R.string.profile_menu));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return true;
    }

    /**
     * Method that set references and listeners
     */
    private void setThings() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemID = item.getItemId();
            Fragment fragment;
            String title = "";
            switch (itemID) {
                case R.id.projects_menu:
                    fragment = new ProjectsFragment();
                    title = getString(R.string.projects_menu);
                    break;
                case R.id.profile_menu:
                    fragment = new ProfileFragment();
                    title = getString(R.string.profile_menu);
                    break;
                case R.id.request_project_menu:
                    fragment = new RequestProjectFragment();
                    title = getString(R.string.request_project_menu);
                    break;
                default:/*case R.id.committees_menu:*/
                    fragment = new CommitteeFragment();
                    title = getString(R.string.committees_menu);
                    break;
            }
            setFragment(fragment, title);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    /**
     * Method that replace fragments, depending the {@link NavigationView}
     *
     * @param fragment fragment to replace
     * @param title    title of the fragment
     */
    private void setFragment(Fragment fragment, String title) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.main_fragment_container, fragment).commit();
        getSupportActionBar().setTitle(title);
    }

    /**
     * Method that search if there is an existing token
     *
     * @return token if it exist, "" otherwise
     */
    private String getToken() {
        return getSharedPreferences(getPackageName(), Context.MODE_PRIVATE)
                .getString(getString(R.string.shared_preferences_key_token), "");
    }

}
