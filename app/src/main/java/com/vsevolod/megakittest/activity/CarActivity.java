/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jrummyapps.android.colorpicker.ColorPickerDialogListener;
import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.constant.Constants;
import com.vsevolod.megakittest.constant.IntentKey;
import com.vsevolod.megakittest.database.DTO;
import com.vsevolod.megakittest.fragment.ReadCarFragment;
import com.vsevolod.megakittest.fragment.UpdateCarFragment;
import com.vsevolod.megakittest.model.Car;

public class CarActivity extends AppCompatActivity implements ColorPickerDialogListener,
        UpdateCarFragment.OnCarReadyToSaveListener, UpdateCarFragment.ChooseImageCallBack {

    private String mAction;

    /**
     * The {@link DTO} that will provide CRUD methods.
     */
    private DTO mDTO;

    /**
     * The {@link Car} Data-object.
     */
    private Car mCar;

    /**
     * The {@link UpdateCarFragment} that will render data-object
     */
    private UpdateCarFragment mUpdateCarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_car);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //init data transfer object
        mDTO = new DTO();

        handleIntent();
    }

    /**
     * Analyzes intent from MainActivity or CarAdapter
     */
    private void handleIntent() {
        mAction = getIntent().getStringExtra(IntentKey.ACTION);
        if (TextUtils.equals(mAction, IntentKey.ACTION_CREATE)) {
            mUpdateCarFragment = new UpdateCarFragment().newInstance(null);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_car, mUpdateCarFragment)
                    .commit();
        } else {
            loadData(getIntent().getStringExtra(IntentKey.ID));
        }
    }

    /**
     * loading data-object by id
     *
     * @param id - data object id
     */
    private void loadData(String id) {
        mCar = mDTO.getCarById(id);

        if (TextUtils.equals(mAction, IntentKey.ACTION_UPDATE)) {
            mUpdateCarFragment = new UpdateCarFragment().newInstance(mCar);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_car, mUpdateCarFragment)
                    .commit();
        } else if (TextUtils.equals(mAction, IntentKey.ACTION_READ)) {
            ReadCarFragment readCarFragment = ReadCarFragment.newInstance(mCar);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_car, readCarFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.remove:
                deleteCar();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * deleting data-object from db
     */
    private void deleteCar() {
        if (mCar != null) {
            String message = getString(R.string.object_deleted
                    , mCar.getManufacturer(), mCar.getModel());

            mDTO.deleteCar(mCar);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void onColorSelected(int dialogId, @ColorInt int color) {
        mUpdateCarFragment.setColor(color);
    }

    @Override
    public void onDialogDismissed(int dialogId) {
        return;
    }

    @Override
    public void onCarReadyToSave(Car car) {
        mDTO.createOrUpdateCar(car);
        mUpdateCarFragment = null;
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri photoUri = data.getData();
            mUpdateCarFragment.setPhoto(photoUri);
        }
    }

    @Override
    public void getImageFromGallery() {
        Intent intent = new Intent();
        intent.setType(Constants.MEDIA_TYPE_IMAGE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                Constants.ACTION_SELECT_PIC), Constants.SELECT_PIC_REQUEST);
    }
}