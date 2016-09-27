package com.sekhar.android.catshala.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.sekhar.android.catshala.utils.FbSignInUtils;
import com.sekhar.android.catshala.utils.GoogleSignInUtils;
import com.sekhar.android.catshala.R;
import com.sekhar.android.catshala.fragment.ExamFragment;
import com.sekhar.android.catshala.fragment.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        ((TextView) header.findViewById(R.id.profile_email_id)).setText(signInAccount.getEmailId());
        ((TextView) header.findViewById(R.id.profile_display_name)).setText(signInAccount.getDisplayName());

        CircleImageView profileImageView = (CircleImageView) header.findViewById(R.id.profile_image);

        if (signInAccount.getPhotoUrl() != null) {
            Glide.with(getApplicationContext()).load(signInAccount.getPhotoUrl())
                    .into(profileImageView);
        }

        switchFragment(new HomeFragment());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navigation_exams) {
            switchFragment(new ExamFragment());
            getSupportActionBar().setTitle(item.getTitle());
        } /*else if (id == R.id.navigation_dashboard) {
            switchFragment(new MathExprFragment());
            getSupportActionBar().setTitle(item.getTitle());
        } */else if (id == R.id.app_sign_out) {
            signOut();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        switch (SignInActivity.getSignInMode()) {
            case GOOGLE: {
                ResultCallback<Status> callback = new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        defaultSignOut();
                    }
                };
                GoogleSignInUtils.signOut(callback);
                break;
            }
            case FACEBOOK:
                FbSignInUtils.signOut();
                defaultSignOut();
                break;
        }
    }

    private void defaultSignOut() {
        BaseActivity.signInAccount = null;
        Intent signInToMain = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(signInToMain);
    }

    private void switchFragment(Fragment fragment) {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).addToBackStack("my_fragment");

        fragmentTransaction.commit();

    }
}
