/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.application;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
