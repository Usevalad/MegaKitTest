/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.fragment;

/**
 * Created by vsevolod on 8/8/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.Repository;
import com.vsevolod.megakittest.adapter.CarAdapter;
import com.vsevolod.megakittest.adapter.DriverAdapter;
import com.vsevolod.megakittest.constant.Constants;
import com.vsevolod.megakittest.view.MyRecyclerView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private static RecyclerView.Adapter mAdapter;

    public PlaceholderFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case Constants.DATA_TYPE_CAR:
                mAdapter = new CarAdapter(getContext(), Repository.getCars());
                break;
            case Constants.DATA_TYPE_DRIVER:
                mAdapter = new DriverAdapter(getContext(), Repository.getDrivers());
                break;
            default:
                break;
        }

        MyRecyclerView recyclerView = new MyRecyclerView(rootView);
        recyclerView.showRecyclerView(mAdapter);
        return rootView;
    }
}