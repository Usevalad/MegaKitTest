/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.vsevolod.megakittest.R;

/**
 * Created by Student Vsevolod on 8/9/17.
 * usevalad.uladzimiravich@gmail.com
 * <p>
 * Contains 3 {@link FloatingActionButton} that triggers activity that
 * create new data objects
 */

public class FABView {
    private FloatingActionButton mFAB;
    private FloatingActionButton mFABCar;
    private FloatingActionButton mFABDriver;
    private Animation mFabOpen;
    private Animation mFabClose;
    private Animation mRotateForward;
    private Animation mRotateBackward;
    private boolean isFabOpen = false;

    /**
     * constructor that init views and set animation
     *
     * @param rootView - to keep views
     */
    public FABView(View rootView) {
        Context context = rootView.getContext();
        //init FABs
        mFAB = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mFABCar = (FloatingActionButton) rootView.findViewById(R.id.fab_car);
        mFABDriver = (FloatingActionButton) rootView.findViewById(R.id.fab_driver);
        //init animation
        mFabOpen = AnimationUtils.loadAnimation(context, R.anim.fab_open);
        mFabClose = AnimationUtils.loadAnimation(context, R.anim.fab_close);
        mRotateForward = AnimationUtils.loadAnimation(context, R.anim.rotate_forward);
        mRotateBackward = AnimationUtils.loadAnimation(context, R.anim.rotate_backward);
    }

    /**
     * set listener
     *
     * @param onClickListener - to trigger activity
     */
    public void setOnClickListeners(View.OnClickListener onClickListener) {
        mFABCar.setOnClickListener(onClickListener);
        mFABDriver.setOnClickListener(onClickListener);
        mFAB.setOnClickListener(onClickListener);
    }

    /**
     * remove listeners
     */
    public void removeOnClickListeners() {
        mFABCar.setOnClickListener(null);
        mFABDriver.setOnClickListener(null);
        mFAB.setOnClickListener(null);
    }


    /**
     * set FAB animation
     */
    public void animateFAB() {
        if (isFabOpen) {
            mFAB.startAnimation(mRotateBackward);
            mFABCar.startAnimation(mFabClose);
            mFABDriver.startAnimation(mFabClose);
            mFABCar.setClickable(false);
            mFABDriver.setClickable(false);
            isFabOpen = false;
        } else {
            mFAB.startAnimation(mRotateForward);
            mFABCar.startAnimation(mFabOpen);
            mFABDriver.startAnimation(mFabOpen);
            mFABCar.setClickable(true);
            mFABDriver.setClickable(true);
            isFabOpen = true;
        }
    }
}