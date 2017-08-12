/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.model;

import com.vsevolod.megakittest.constant.Constants;
import com.vsevolod.megakittest.util.MyDateUtil;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class Driver extends RealmObject{
    @PrimaryKey
    private String uid;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String date;
    private RealmList<Car> cars;

    public Driver(String firstName, String lastName, String phoneNumber, RealmList<Car> cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.cars = cars;
        this.uid = UUID.randomUUID().toString();
        this.date = MyDateUtil.getSearchDate();
    }

    public Driver(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.uid = UUID.randomUUID().toString();
        this.date = MyDateUtil.getSearchDate();
    }

    /**
     * Realm needs a public constructor with no arguments
     */
    public Driver() {
        this.uid = UUID.randomUUID().toString();
        this.date = MyDateUtil.getSearchDate();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}