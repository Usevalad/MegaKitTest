package com.vsevolod.megakittest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.model.Car;
import com.vsevolod.megakittest.view.CarView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadCarFragment extends Fragment {
    /**
     * {@link Car} data-object
     */
    private static Car mCar;

    public ReadCarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReadCarFragment.
     */
    public static ReadCarFragment newInstance(Car car) {
        mCar = car;
        return new ReadCarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_read_car, container, false);

        CarView mCarView = new CarView(rootView, null);
        mCarView.showCar(mCar);

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}