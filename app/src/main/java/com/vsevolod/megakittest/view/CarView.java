/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.constant.Constants;
import com.vsevolod.megakittest.model.Car;
import com.vsevolod.megakittest.util.PicassoClient;
import com.vsevolod.megakittest.util.Validator;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 * <p>
 * Contains all views necessary to display/update/ create data-objects
 */

public class CarView {
    private final Context mContext;
    private final View mRootView;
    private Car mCar;
    private int mColor;
    private String mUri;

    private ImageView mCarImageView;

    private ImageButton mAddImageButton;
    private ImageButton mColorImageButton;

    private Button mSubmitButton;

    private EditText mManufacturerEditText;
    private EditText mModelEditText;
    private EditText mBodyTypeEditText;

    private View.OnClickListener mClickListener;

    /**
     * init data
     *
     * @param rootView - to keep views
     * @param listener - to trigger Activity
     */
    public CarView(View rootView, View.OnClickListener listener) {
        //get context
        this.mContext = rootView.getContext();
        //init root view
        this.mRootView = rootView;
        //init listener
        this.mClickListener = listener;
        //init views
        this.mCarImageView = (ImageView) mRootView.findViewById(R.id.car_image_view);
        this.mColorImageButton = (ImageButton) mRootView.findViewById(R.id.color_button);
    }


    /**
     * to read car-object
     *
     * @param car - car-object
     */
    public void showCar(Car car) {
        if (car != null) {
            //init data object
            mCar = car;
            //init views
            TextView mManufacturerTextView = (TextView) mRootView.findViewById(R.id.car_manufacturer);
            TextView mModelTextView = (TextView) mRootView.findViewById(R.id.car_model);
            TextView mBodyTypeTextView = (TextView) mRootView.findViewById(R.id.car_body_type);
            //get strings
            String bodyType, model, manufacturer;
            bodyType = mContext.getString(R.string.main_car_body_type, car.getBodyType());
            model = mContext.getString(R.string.main_car_model, car.getModel());
            manufacturer = mContext.getString(R.string.main_car_manufacturer, car.getManufacturer());
            //set ImageButton color
            setButtonColor(car.getColor());
            //set text in TextViews
            mBodyTypeTextView.setText(bodyType);
            mModelTextView.setText(model);
            mManufacturerTextView.setText(manufacturer);
            //set image
            PicassoClient.downloadImage(mContext, car.getUri(), mCarImageView);
        }
    }

    /**
     * update existing or create new car-object
     *
     * @param car - car-object
     */
    public void updateOrCreateCar(Car car) {
        //init data-object
        mCar = car;
        //init views
        mAddImageButton = (ImageButton) mRootView.findViewById(R.id.car_add_image_button);
        mSubmitButton = (Button) mRootView.findViewById(R.id.submit_button);
        mManufacturerEditText = (EditText) mRootView.findViewById(R.id.car_manufacturer);
        mModelEditText = (EditText) mRootView.findViewById(R.id.car_model);
        mBodyTypeEditText = (EditText) mRootView.findViewById(R.id.car_body_type);
        //set image
        mCarImageView.setImageResource(R.drawable.tesla);
        //set listeners
        mAddImageButton.setOnClickListener(mClickListener);
        mColorImageButton.setOnClickListener(mClickListener);
        mSubmitButton.setOnClickListener(mClickListener);

        if (car != null) {
            //get strings
            String bodyType, model, manufacturer;
            bodyType = car.getBodyType();
            model = car.getModel();
            manufacturer = car.getManufacturer();
            //set color picker (Image Button) color
            setButtonColor(car.getColor());
            //set text
            mManufacturerEditText.setText(manufacturer);
            mModelEditText.setText(model);
            mBodyTypeEditText.setText(bodyType);
            //set image
            PicassoClient.downloadImage(mContext, car.getUri(), mCarImageView);
        }
    }

    /**
     * set ImageButton color, that triggered color picker
     *
     * @param color - color to set
     */
    public void setButtonColor(int color) {
        mColor = color;
        Drawable d = mColorImageButton.getDrawable();
        d.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        mColorImageButton.setBackground(d);
    }

    /**
     * set image to ImageView
     *
     * @param uri - image path
     */
    public void setPhoto(Uri uri) {
        mUri = uri.toString();
        PicassoClient.downloadImage(mContext, mUri, mCarImageView);
    }

    /**
     * Validate strings from EditText views
     *
     * @param onFieldsValidatedListener - callback that trigger activity to save data
     */
    public void validateFields(OnFieldsValidatedListener onFieldsValidatedListener) {
        String manufacturer = Validator.validateInput(mManufacturerEditText);
        String model = Validator.validateInput(mModelEditText);
        String bodyType = Validator.validateInput(mBodyTypeEditText);

        if (mManufacturerEditText.getError() == null
                && mModelEditText.getError() == null
                && mBodyTypeEditText.getError() == null) {
            mUri = mUri != null ? mUri : Constants.URI;  //if uri == null -> set random car uri
            saveCar(onFieldsValidatedListener, manufacturer, model, bodyType, mColor);
        }
    }

    /**
     * create new or update  existing data
     *
     * @param listener     - callback that trigger activity to save data
     * @param manufacturer - data field
     * @param model        - data field
     * @param bodyType     - data field
     * @param color        - data field
     */
    private void saveCar(OnFieldsValidatedListener listener, String manufacturer, String model,
                         String bodyType, int color) {
        if (mCar != null) {
            Car car = new Car(bodyType, color, model, manufacturer, mUri);
            car.setId(mCar.getId());
            car.setDate(mCar.getDate());
            listener.onFieldsValidated(car);
        } else {
            listener.onFieldsValidated(new Car(bodyType, color, model, manufacturer, mUri));
        }
    }

    /**
     * remove listeners
     */
    public void removeListeners() {
        if (mAddImageButton != null)
            mAddImageButton.setOnClickListener(null);
        if (mColorImageButton != null)
            mColorImageButton.setOnClickListener(null);
        if (mSubmitButton != null)
            mSubmitButton.setOnClickListener(null);
    }

    /**
     * interface that trigger activity to save data
     */
    public interface OnFieldsValidatedListener {
        void onFieldsValidated(Car car);
    }
}