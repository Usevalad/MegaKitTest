/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.view;

import android.view.View;
import android.widget.ProgressBar;

import com.vsevolod.megakittest.R;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class ProgressBarView {
    private final ProgressBar mProgressBar;

    public ProgressBarView(View rootView) {
        this.mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
    }

    public void hide() {
        mProgressBar.setVisibility(View.GONE);
    }

    public void show() {
        mProgressBar.setVisibility(View.VISIBLE);
    }
}