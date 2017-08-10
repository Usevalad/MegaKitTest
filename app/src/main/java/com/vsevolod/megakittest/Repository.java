/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest;

import com.vsevolod.megakittest.model.Car;
import com.vsevolod.megakittest.model.Driver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class Repository {
//    public static Car car0 = new Car("x5", "BMW");
//    public static Car car1 = new Car("x3", "BMW");
//    public static Car car2 = new Car("leaf", "Nissan");
//    public static Car car3 = new Car("model S", "Tesla");
//    public static Car car4 = new Car("model X", "Tesla");
//    public static Car car5 = new Car("impreza", "subaru");

//    public static Driver driver0 = new Driver("Джохар", "Дудаев");
//    public static Driver driver1 = new Driver("Тимур", "Муцураев");
//    public static Driver driver2 = new Driver("Джеки", "Чан");
//    public static Driver driver3 = new Driver("Кро", "Коп");
//    public static Driver driver4 = new Driver("Джон", "Сноу");
//    public static Driver driver5 = new Driver("Джим", "Керри");

    public static List<Driver> getDrivers() {
        List<Driver> drivers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            drivers.add(new Driver("firstName " + i, "lastName " + i,
                    "+380674650844"));
        }
        return drivers;
    }

    public static List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cars.add(new Car(
                    "body type " + i,
                    "color " + i,
                    "model " + i,
                    "manufacturer " + i));
        }
        return cars;
    }

    public static String getCarPhoto() {
        return "http://autotesla.com/wp-content/uploads/149631-1.jpg";
    }
}