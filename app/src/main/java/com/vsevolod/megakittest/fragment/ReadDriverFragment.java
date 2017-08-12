package com.vsevolod.megakittest.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.model.Driver;
import com.vsevolod.megakittest.view.DriverView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadDriverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadDriverFragment extends Fragment implements View.OnClickListener {

    /**
     * {@link Driver} data-object
     */
    private static Driver mDriver;

    /**
     * {@link DriverView} that will init, keep and manage all views
     * that involved in Driver-object rendering
     *
     */
    private DriverView mDriverView;

    public ReadDriverFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReadDriverFragment.
     */
    public static ReadDriverFragment newInstance(Driver driver) {
        mDriver = driver;
        return new ReadDriverFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_read_driver, container, false);
        //init view
        mDriverView = new DriverView(rootView);
        //show loaded data
        mDriverView.showDriver(mDriver, this);
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //remove listeners
        mDriverView.removerListeners();
    }

    @Override
    public void onClick(View v) {
        startCallActivity();
    }

    private void startCallActivity() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mDriver.getPhoneNumber()));
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {

            getContext().startActivity(intent);
        }
    }
}