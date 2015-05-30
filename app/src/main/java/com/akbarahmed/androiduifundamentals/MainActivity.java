package com.akbarahmed.androiduifundamentals;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // App bar / Toolbar / ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_app_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }

//        // NavigationDrawer
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        NavigationDrawerFragment navigationDrawer = NavigationDrawerFragment.newInstance("ph");
        ft.add(R.id.activity_main_navigation_drawer, navigationDrawer);
        ft.commit();

        // NavigationDrawer
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        navigationDrawer.setup(MainActivity.this, R.id.activity_main_navigation_drawer, drawerLayout, toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //
        // This code works as the Activity has been created by the time onStart() is called.
        // Specifically, getActivity() in the Fragment will return this activity. However, this
        // code will be executed during both the create and restart workflows.
        //
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_app_bar);

        // NavigationDrawer
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);

        FragmentManager fm = getSupportFragmentManager();
        NavigationDrawerFragment navigationDrawer = (NavigationDrawerFragment)
                fm.findFragmentById(R.id.activity_main_navigation_drawer);
        navigationDrawer.setup(R.id.activity_main_navigation_drawer, drawerLayout, toolbar);
        */
//        navigationDrawer.checkContext(MainActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        } else if (id == R.id.action_next) {
            startActivity(new Intent(this, BActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    // Callbacks


    @Override
    public void onFragmentInteraction(Uri uri) {
        // do nothing
    }
}
