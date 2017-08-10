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
import com.vsevolod.megakittest.model.Car;
import com.vsevolod.megakittest.provider.CarProvider;
import com.vsevolod.megakittest.view.CarView;
import com.vsevolod.megakittest.view.ProgressBarView;

public class CarActivity extends AppCompatActivity implements CarProvider.Callback, View.OnClickListener {
    private View mRootView;
    private ProgressBarView mProgressBarView;
    private String mAction;
    private CarView mCarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRootView = getWindow().getDecorView();
        mProgressBarView = new ProgressBarView(mRootView);
        mCarView = new CarView(mRootView);

        handleIntent();
    }

    private void handleIntent() {
        mAction = getIntent().getStringExtra(IntentKey.ACTION);
        if (TextUtils.equals(mAction, IntentKey.CREATE))
            mCarView.createCar();
        else
            loadData(getIntent().getLongExtra(IntentKey.ID, 0));
    }

    private void loadData(long id) {
        mProgressBarView.show();
        CarProvider carProvider = new CarProvider();
        carProvider.getCar(this, id);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCarLoaded(Car car) {
        mProgressBarView.hide();
        if (TextUtils.equals(mAction, IntentKey.UPDATE))
            mCarView.updateCar(car);
        else if (TextUtils.equals(mAction, IntentKey.READ))
            mCarView.showCar(car);
    }
}