/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.model;

import com.vsevolod.megakittest.constant.Constants;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class Driver extends RealmObject implements Model {
    @PrimaryKey
    private long uid;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private RealmList<Car> cars;

    public Driver(String firstName, String lastName, String phoneNumber, RealmList<Car> cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.cars = cars;
    }

    public Driver(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Realm needs a public constructor with no arguments
     */
    public Driver() {
    }

    public long getId() {
        return uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int getDataType() {
        return Constants.DATA_TYPE_DRIVER;
    }
}