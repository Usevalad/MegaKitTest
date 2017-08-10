/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.model;

import com.vsevolod.megakittest.constant.Constants;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */
public class Car extends RealmObject implements Model {
    @PrimaryKey
    private long uid;
    private String bodyType;
    private String color;
    private String model;
    private String manufacturer;

    public Car(String bodyType, String color,
               String model, String manufacturer) {
        this.bodyType = bodyType;
        this.color = color;
        this.model = model;
        this.manufacturer = manufacturer;
    }

    /**
     * Realm needs a public constructor with no arguments
     */
    public Car() {
    }

    public long getId() {
        return uid;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Car:  \n" +
                "manufacturer: " + manufacturer + "\n" +
                "model: " + model + "\n" +
                "bodyType: " + bodyType + "\n" +
                "color: " + color;
    }

    @Override
    public int getDataType() {
        return Constants.DATA_TYPE_CAR;
    }
}