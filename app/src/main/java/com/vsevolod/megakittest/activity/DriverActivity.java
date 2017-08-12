/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.constant.IntentKey;
import com.vsevolod.megakittest.database.DTO;
import com.vsevolod.megakittest.fragment.ReadDriverFragment;
import com.vsevolod.megakittest.fragment.UpdateDriverFragment;
import com.vsevolod.megakittest.model.Driver;

public class DriverActivity extends AppCompatActivity implements
        UpdateDriverFragment.OnDriverReadyToSaveListener {
    private String mAction;
    //data transfer object
    private DTO mDTO;
    //data object
    private Driver mDriver;
    private UpdateDriverFragment mUpdateDriverFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_driver);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //init data transfer object
        mDTO = new DTO();

        handleIntent();
    }

    private void loadData(String id) {
        mDriver = mDTO.getDriverById(id);
        if (TextUtils.equals(mAction, IntentKey.ACTION_UPDATE)) {
            mUpdateDriverFragment = new UpdateDriverFragment().newInstance(mDriver);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_driver, mUpdateDriverFragment)
                    .commit();
        } else if (TextUtils.equals(mAction, IntentKey.ACTION_READ)) {
            ReadDriverFragment readDriverFragment = new ReadDriverFragment().newInstance(mDriver);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_driver, readDriverFragment)
                    .commit();
        }
    }

    /**
     * Analyzes intent from MainActivity or CarAdapter
     */
    private void handleIntent() {
        mAction = getIntent().getStringExtra(IntentKey.ACTION);

        if (TextUtils.equals(mAction, IntentKey.ACTION_CREATE)) {
            mUpdateDriverFragment = new UpdateDriverFragment().newInstance(null);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_driver, mUpdateDriverFragment)
                    .commit();
        } else {
            loadData(getIntent().getStringExtra(IntentKey.ID));
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
                deleteDriver();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * deleting data-object from db
     */
    private void deleteDriver() {
        if (mDriver != null) {
            String message = getString(R.string.object_deleted
                    , mDriver.getFirstName(), mDriver.getLastName());
            mDTO.deleteDriver(mDriver);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void onDriverReadyToSave(Driver driver) {
        mDTO.createOrUpdateDriver(driver);
        mUpdateDriverFragment = null;
        finish();
    }
}