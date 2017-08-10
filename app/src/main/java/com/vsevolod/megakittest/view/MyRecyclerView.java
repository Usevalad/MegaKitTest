/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.view;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.application.MyApplication;

/**
 * Created by Student Vsevolod on 8/9/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class MyRecyclerView {
    private RecyclerView mRecyclerView;

    public MyRecyclerView(View rootView) {
        this.mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
    }

    public void showRecyclerView(RecyclerView.Adapter adapter) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }
}