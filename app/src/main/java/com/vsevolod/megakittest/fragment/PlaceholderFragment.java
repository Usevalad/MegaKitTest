/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.adapter.CarAdapter;
import com.vsevolod.megakittest.adapter.DriverAdapter;
import com.vsevolod.megakittest.database.DTO;
import com.vsevolod.megakittest.model.Car;
import com.vsevolod.megakittest.model.Driver;
import com.vsevolod.megakittest.view.MyRecyclerView;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 * <p>
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements CarAdapter.DeleteCarCallback,
        DriverAdapter.Callback {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * * {@link RecyclerView.Adapter} that will manage data
     */
    private static RecyclerView.Adapter mAdapter;

    /**
     * * {@link DTO} that will provide CRUD methods
     */
    private DTO mDTO;

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
        mDTO = new DTO();

        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 0: //adapter position
                mAdapter = new CarAdapter(getContext(), mDTO.getCars(), this);
                break;
            case 1: //adapter position
                mAdapter = new DriverAdapter(getContext(), mDTO.getDrivers(), this);
                break;
            default:
                break;
        }

        MyRecyclerView recyclerView = new MyRecyclerView(rootView);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        mDTO.close();
        super.onDestroyView();
    }

    @Override
    public void deleteCar(Car car) {
        mDTO.deleteCar(car);
    }

    @Override
    public void deleteDriver(Driver driver) {
        mDTO.deleteDriver(driver);
    }
}