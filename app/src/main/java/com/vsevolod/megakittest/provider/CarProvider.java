/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.provider;

import android.os.AsyncTask;

import com.vsevolod.megakittest.Repository;
import com.vsevolod.megakittest.model.Car;

import java.util.List;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class CarProvider {

    public void getCar(Callback callback, long id) {
        new LoadCarTask(callback, id).execute();
    }

    class LoadCarTask extends AsyncTask<Void, Void, Car> {
        private Callback callback;
        private long id;

        public LoadCarTask(Callback callback, long id) {
            this.callback = callback;
            this.id = id;
        }

        @Override
        protected Car doInBackground(Void... params) {
            List<Car> cars = Repository.getCars();
            for (Car car : cars) {
                if (car.getId() == id) {
                    return car;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Car car) {
            super.onPostExecute(car);
            callback.onCarLoaded(car);
        }
    }

    public interface Callback {
        void onCarLoaded(Car car);
    }
}