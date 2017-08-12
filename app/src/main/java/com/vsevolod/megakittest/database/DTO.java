package com.vsevolod.megakittest.database;

import com.vsevolod.megakittest.application.MyApplication;
import com.vsevolod.megakittest.model.Car;
import com.vsevolod.megakittest.model.Driver;

import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * Created by Student Vsevolod on 8/10/17.
 * usevalad.uladzimiravich@gmail.com
 * <p>
 * DTO - Data Transfer Object
 * provide CRUD methods
 */

public class DTO {
    /**
     * The {@link Realm} that will host data.
     */
    private Realm mRealm;

    /**
     * Fields of data-objects for search
     */
    private final String FIELD_DATE = "date";
    private final String FIELD_ID = "uid";

    /**
     * init DTO
     */
    public DTO() {
        Realm.init(MyApplication.getContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * close when you'll finish work with Realm-object
     */
    public void close() {
        mRealm.close();
    }

    /**
     * @return List of all Driver objects from db
     */
    public List<Driver> getDrivers() {
        RealmQuery dataQuery = mRealm.where(Driver.class);
        return dataQuery.findAllSorted(FIELD_DATE, Sort.DESCENDING);
    }

    /**
     * @return List of all Car objects from db
     */
    public List<Car> getCars() {
        RealmQuery dataQuery = mRealm.where(Car.class);
        return dataQuery.findAllSorted(FIELD_DATE, Sort.DESCENDING);
    }

    /**
     * Search Data by id field
     *
     * @param id - Car-object UID
     * @return Car-object
     */
    public Car getCarById(String id) {
        RealmQuery idQuery = mRealm.where(Car.class);
        idQuery.equalTo(FIELD_ID, id, Case.SENSITIVE);
        Car car;
        try {
            car = (Car) idQuery.findFirst();
            return car;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Search Data by id field
     *
     * @param id - Driver-object UID
     * @return Driver-object
     */
    public Driver getDriverById(String id) {
        RealmQuery idQuery = mRealm.where(Driver.class);
        idQuery.equalTo(FIELD_ID, id, Case.SENSITIVE);
        Driver driver;
        try {
            driver = (Driver) idQuery.findFirst();
            return driver;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Delete Car-object from db
     *
     * @param car - object to delete
     */
    public void deleteCar(Car car) {
        mRealm.beginTransaction();
        car.deleteFromRealm();
        mRealm.commitTransaction();
    }

    /**
     * Delete Driver-object from db
     *
     * @param driver - object to delete
     */
    public void deleteDriver(Driver driver) {
        mRealm.beginTransaction();
        driver.deleteFromRealm();
        mRealm.commitTransaction();
    }

    /**
     * Delete all Car-objects from db
     */
    public void deleteAllCars() {
        RealmResults<Car> results = mRealm.where(Car.class).findAll();
        // All changes to data must happen in a transaction
        mRealm.beginTransaction();
        // Delete all matches
        results.deleteAllFromRealm();
        mRealm.commitTransaction();
    }

    /**
     * Delete all Driver-objects from db
     */
    public void deleteAllDrivers() {
        RealmResults<Driver> results = mRealm.where(Driver.class).findAll();
        // All changes to data must happen in a transaction
        mRealm.beginTransaction();
        // Delete all matches
        results.deleteAllFromRealm();
        mRealm.commitTransaction();
    }

    /**
     * Create or Update existing Driver-object
     *
     * @param driver - Driver-object to save/update
     */
    public void createOrUpdateDriver(Driver driver) {
        mRealm.beginTransaction();
        Driver a = mRealm.copyToRealmOrUpdate(driver);
        a.toString();
        mRealm.commitTransaction();
    }

    /**
     * Create or Update existing Car-object
     *
     * @param car - Car-object to save/update
     */
    public void createOrUpdateCar(Car car) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(car);
        mRealm.commitTransaction();
    }
}