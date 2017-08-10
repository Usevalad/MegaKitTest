/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.constant.IntentKey;
import com.vsevolod.megakittest.model.Driver;
import com.vsevolod.megakittest.provider.DriverProvider;
import com.vsevolod.megakittest.view.DriverView;
import com.vsevolod.megakittest.view.ProgressBarView;

public class DriverActivity extends AppCompatActivity implements DriverProvider.Callback, View.OnClickListener {
    private View mRootView;
    private ProgressBarView mProgressBarView;
    private DriverView mDriverView;
    private String mAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRootView = getWindow().getDecorView();
        mProgressBarView = new ProgressBarView(mRootView);
        mDriverView = new DriverView(mRootView);

        handleIntent();
    }

    private void loadData(long id) {
        DriverProvider driverProvider = new DriverProvider();
        mProgressBarView.show();
        driverProvider.getDriver(this, id);
    }

    private void handleIntent() {
        mAction = getIntent().getStringExtra(IntentKey.ACTION);
        if (TextUtils.equals(mAction, IntentKey.CREATE))
            mDriverView.createDriver();
        else
            loadData(getIntent().getLongExtra(IntentKey.ID, 0));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDriverLoaded(Driver driver) {
        mProgressBarView.hide();
        if (TextUtils.equals(mAction, IntentKey.UPDATE))
            mDriverView.updateDriver(driver);
        else if (TextUtils.equals(mAction, IntentKey.READ))
            mDriverView.showDriver(driver);
    }
}