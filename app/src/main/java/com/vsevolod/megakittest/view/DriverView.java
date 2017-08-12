/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.model.Driver;
import com.vsevolod.megakittest.util.Validator;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class DriverView {
    private View mRootView;
    private Context mContext;

    private TextView mFirstNameTextView;
    private TextView mLastNameTextView;
    private TextView mPhoneNumberTextView;

    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneNumberEditText;

    private Button mSubmitButton;

    private Driver mDriver;

    /**
     * Init data
     *
     * @param rootView - to keep views
     */
    public DriverView(View rootView) {
        //init rootView
        this.mRootView = rootView;
        //init context
        this.mContext = rootView.getContext();
        //init views
        this.mFirstNameTextView = (TextView) rootView.findViewById(R.id.first_name_text_view);
        this.mLastNameTextView = (TextView) rootView.findViewById(R.id.last_name_text_view);
        this.mPhoneNumberTextView = (TextView) rootView.findViewById(R.id.phone_number_text_view);
    }


    /**
     * read driver-object
     *
     * @param driver   - driver-object
     * @param listener - to trigger activity
     */
    public void showDriver(Driver driver, View.OnClickListener listener) {
        if (driver != null) {
            //init data-object
            mDriver = driver;
            //get strings
            String firstName, lastName, phoneNumber;
            firstName = mContext.getString(R.string.driver_first_name_arg, driver.getFirstName());
            lastName = mContext.getString(R.string.driver_last_name_arg, driver.getLastName());
            phoneNumber = mContext.getString(R.string.driver_phone_number_arg, driver.getPhoneNumber());
            //set strings to views
            mFirstNameTextView.setText(firstName);
            mLastNameTextView.setText(lastName);
            mPhoneNumberTextView.setText(phoneNumber);
            //set listener
            mPhoneNumberTextView.setOnClickListener(listener);
        }
    }


    /**
     * update existing or create new driver-object
     *
     * @param driver   - driver-object
     * @param listener - to trigger activity
     */
    public void updateOrCreateDriver(Driver driver, View.OnClickListener listener) {
        //init data
        mDriver = driver;
        //init views
        mFirstNameEditText = (EditText) mRootView.findViewById(R.id.first_name_edit_text);
        mLastNameEditText = (EditText) mRootView.findViewById(R.id.last_name_edit_text);
        mPhoneNumberEditText = (EditText) mRootView.findViewById(R.id.phone_number_edit_text);
        mSubmitButton = (Button) mRootView.findViewById(R.id.submit_button);
        //set listener
        mSubmitButton.setOnClickListener(listener);

        if (driver != null) {
            //set text to views
            mFirstNameEditText.setText(driver.getFirstName());
            mLastNameEditText.setText(driver.getLastName());
            mPhoneNumberEditText.setText(driver.getPhoneNumber());
        }
    }

    /**
     * Validate strings from EditText views
     *
     * @param onFieldsValidateListener - callback that trigger activity to save data
     */
    public void validateFields(OnFieldsValidateListener onFieldsValidateListener) {
        String firstName = Validator.validateInput(mFirstNameEditText);
        String lastName = Validator.validateInput(mLastNameEditText);
        String phoneNumber = Validator.validatePhone(mPhoneNumberEditText);

        if (mPhoneNumberEditText.getError() == null
                && mFirstNameEditText.getError() == null
                && mLastNameEditText.getError() == null) {

            saveDriver(onFieldsValidateListener, firstName, lastName, phoneNumber);
        }
    }


    /**
     * create new or update  existing data
     *
     * @param onFieldsValidateListener - callback that trigger activity to save data
     * @param firstName                - data field
     * @param lastName                 - data field
     * @param phoneNumber              - data field
     */
    private void saveDriver(OnFieldsValidateListener onFieldsValidateListener,
                            String firstName, String lastName, String phoneNumber) {
        if (mDriver != null) {
            Driver driver = new Driver(firstName, lastName, phoneNumber);
            driver.setUid(mDriver.getId());
            driver.setDate(mDriver.getDate());
            onFieldsValidateListener.onFieldsValidated(driver);
        } else {
            onFieldsValidateListener.onFieldsValidated(new Driver(firstName, lastName, phoneNumber));
        }
    }

    /**
     * remove listeners
     */
    public void removerListeners() {
        if (mSubmitButton != null)
            mSubmitButton.setOnClickListener(null);
        if (mPhoneNumberTextView != null)
            mPhoneNumberTextView.setOnClickListener(null);
    }

    /**
     * interface that trigger activity to save data
     */
    public interface OnFieldsValidateListener {
        void onFieldsValidated(Driver driver);
    }
}