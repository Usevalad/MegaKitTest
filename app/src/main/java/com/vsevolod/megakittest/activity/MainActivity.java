/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.adapter.SectionsPagerAdapter;
import com.vsevolod.megakittest.constant.Constants;
import com.vsevolod.megakittest.constant.IntentKey;
import com.vsevolod.megakittest.database.DTO;
import com.vsevolod.megakittest.view.FABView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    /**
     * The {@link FABView} that will set FloatingActionButtons, animation, listeners.
     */
    private FABView mFAB;

    /**
     * The {@link DTO} data CRUD methods
     */
    private DTO mDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //set up FloatingActionButtons
        mFAB = new FABView(getWindow().getDecorView());
        mFAB.setOnClickListeners(this);

        mDTO = new DTO();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFAB.removeOnClickListeners();
        mDTO.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewPagerAdapter();
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
        switch (item.getItemId()) {
            case R.id.delete_cars:
                mDTO.deleteAllCars();
                setViewPagerAdapter();
                return true;
            case R.id.delete_drivers:
                mDTO.deleteAllDrivers();
                setViewPagerAdapter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                mFAB.animateFAB();
                break;
            case R.id.fab_car:
                startNewActivity(Constants.DATA_TYPE_CAR);
                break;
            case R.id.fab_driver:
                startNewActivity(Constants.DATA_TYPE_DRIVER);
                break;
            default:
                break;
        }
    }

    /**
     * Starting new activity depends on data-object type
     *
     * @param dataType - The type of the data object to be created
     */
    private void startNewActivity(int dataType) {
        Class activity = dataType == Constants.DATA_TYPE_CAR ? CarActivity.class : DriverActivity.class;
        Intent intent = new Intent(this, activity);
        intent.putExtra(IntentKey.ACTION, IntentKey.ACTION_CREATE);
        startActivity(intent);
    }

    /**
     * Updates after changes
     */
    private void setViewPagerAdapter() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }
}