/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.view;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.model.Driver;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class DriverView {
    private final Context mContext;
    private final TextView mFirstNameTextView;
    private final TextView mLastNameTextView;
    private final TextView mPhoneNumberTextView;
    private final EditText mFirstNameEditText;
    private final EditText mLastNameEditText;
    private final EditText mPhoneNumberEditText;

    public DriverView(View rootView) {
        this.mContext = rootView.getContext();
        this.mFirstNameTextView = (TextView) rootView.findViewById(R.id.details_driver_first_name_text_view);
        this.mLastNameTextView = (TextView) rootView.findViewById(R.id.details_driver_last_name_text_view);
        this.mPhoneNumberTextView = (TextView) rootView.findViewById(R.id.details_driver_phone_number_text_view);
        this.mFirstNameEditText = (EditText) rootView.findViewById(R.id.details_driver_first_name_edit_text);
        this.mLastNameEditText = (EditText) rootView.findViewById(R.id.details_driver_last_name_edit_text);
        this.mPhoneNumberEditText = (EditText) rootView.findViewById(R.id.details_driver_phone_number_edit_text);
    }

    public void showDriver(Driver driver) {
        if (driver != null) {
            mFirstNameEditText.setVisibility(View.GONE);
            mLastNameEditText.setVisibility(View.GONE);
            mPhoneNumberEditText.setVisibility(View.GONE);
            mFirstNameTextView.setVisibility(View.VISIBLE);
            mLastNameTextView.setVisibility(View.VISIBLE);

            mFirstNameTextView.setText(driver.getFirstName());
            mLastNameTextView.setText(driver.getLastName());
            mPhoneNumberTextView.setText(driver.getPhoneNumber());
        }
    }

    public void updateDriver(Driver driver) {
        if (driver != null) {
            mFirstNameEditText.setVisibility(View.VISIBLE);
            mLastNameEditText.setVisibility(View.VISIBLE);
            mFirstNameTextView.setVisibility(View.GONE);
            mLastNameTextView.setVisibility(View.GONE);

            mFirstNameEditText.setText(driver.getFirstName());
            mLastNameEditText.setText(driver.getLastName());
            mPhoneNumberEditText.setError(driver.getPhoneNumber());
        }
    }

    public void createDriver() {
        mFirstNameEditText.setVisibility(View.VISIBLE);
        mLastNameEditText.setVisibility(View.VISIBLE);
        mPhoneNumberEditText.setVisibility(View.VISIBLE);
        mFirstNameTextView.setVisibility(View.GONE);
        mLastNameTextView.setVisibility(View.GONE);
        mPhoneNumberTextView.setVisibility(View.GONE);
    }
}