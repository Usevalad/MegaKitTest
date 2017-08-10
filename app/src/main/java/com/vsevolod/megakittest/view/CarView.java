/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.Repository;
import com.vsevolod.megakittest.model.Car;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class CarView {
    private final Context mContext;
    private final ImageView mCarImageView;
    private final TextView mManufacturerTextView;
    private final TextView mModelTextView;
    private final TextView mBodyTypeTextView;

    public CarView(View rootView) {
        this.mContext = rootView.getContext();
        this.mCarImageView = (ImageView) rootView.findViewById(R.id.details_car_image_view);
        this.mManufacturerTextView = (TextView) rootView.findViewById(R.id.details_car_manufacturer_text_view);
        this.mModelTextView = (TextView) rootView.findViewById(R.id.details_car_model_text_view);
        this.mBodyTypeTextView = (TextView) rootView.findViewById(R.id.details_car_body_type_text_view);
    }

    // FIXME: 8/8/17 добавать заглушку в пикассо
    public void showCar(Car car) {
        if (car != null) {
            String bodyType, model, manufacturer;
            bodyType = mContext.getString(R.string.car_body_type, car.getBodyType());
            model = mContext.getString(R.string.car_model, car.getModel());
            manufacturer = mContext.getString(R.string.car_manufacturer, car.getManufacturer());

            mBodyTypeTextView.setText(bodyType);
            mModelTextView.setText(model);
            mManufacturerTextView.setText(manufacturer);
            Picasso.with(mContext)
                    .load(Repository.getCarPhoto())
                    .into(mCarImageView);
        }
    }

    public void updateCar(Car car) {// FIXME: 8/9/17 add edit views
        if (car != null) {
            String bodyType, model, manufacturer;
            bodyType = mContext.getString(R.string.car_body_type, car.getBodyType());
            model = mContext.getString(R.string.car_model, car.getModel());
            manufacturer = mContext.getString(R.string.car_manufacturer, car.getManufacturer());

            mBodyTypeTextView.setText(bodyType);
            mModelTextView.setText(model);
            mManufacturerTextView.setText(manufacturer);
            Picasso.with(mContext)
                    .load(Repository.getCarPhoto())
                    .into(mCarImageView);
        }
    }

    public void createCar() {

    }
}