/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.model;

import com.vsevolod.megakittest.util.MyDateUtil;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */
public class Car extends RealmObject {
    @PrimaryKey
    private String uid;
    private String bodyType;
    private int color;
    private String model;
    private String manufacturer;
    private String date;
    private String uri;

    public Car(String bodyType, int color,
               String model, String manufacturer, String uri) {
        this.bodyType = bodyType;
        this.color = color;
        this.model = model;
        this.manufacturer = manufacturer;
        this.uri = uri;
        this.date = MyDateUtil.getSearchDate();
        this.uid = UUID.randomUUID().toString();
    }

    /**
     * Realm needs a public constructor with no arguments
     */
    public Car() {
        this.date = MyDateUtil.getSearchDate();
        this.uid = UUID.randomUUID().toString();
    }

    public String getUri() {
        return uri;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return uid;
    }

    public void setId(String uid) {
        this.uid = uid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBodyType() {
        return bodyType;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
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
}