/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.provider;

import android.os.AsyncTask;

import com.vsevolod.megakittest.Repository;
import com.vsevolod.megakittest.model.Driver;

import java.util.List;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class DriverProvider {

    public void getDriver(Callback callback, long id) {
        new LoadDriverTask(callback, id).execute();
    }

    class LoadDriverTask extends AsyncTask<Void, Void, Driver> {
        private Callback callback;
        private long id;

        public LoadDriverTask(Callback callback, long id) {
            this.callback = callback;
            this.id = id;
        }

        @Override
        protected Driver doInBackground(Void... params) {
            List<Driver> drivers = Repository.getDrivers();
            for (Driver driver : drivers) {
                if (driver.getId() == id) {
                    return driver;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Driver driver) {
            super.onPostExecute(driver);
            callback.onDriverLoaded(driver);
        }
    }

    public interface Callback {
        void onDriverLoaded(Driver driver);
    }
}