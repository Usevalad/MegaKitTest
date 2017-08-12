package com.vsevolod.megakittest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.model.Driver;
import com.vsevolod.megakittest.view.DriverView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnDriverReadyToSaveListener} interface
 * to handle interaction events.
 * Use the {@link UpdateDriverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateDriverFragment extends Fragment implements View.OnClickListener,
        DriverView.OnFieldsValidateListener {

    private OnDriverReadyToSaveListener mListener;

    /**
     * {@link Driver} data-object
     */
    private static Driver mDriver;

    /**
     * {@link DriverView} that will init, keep and manage all views
     * that involved in Driver-object rendering
     */
    private DriverView mDriverView;

    public UpdateDriverFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UpdateDriverFragment.
     */
    public static UpdateDriverFragment newInstance(Driver driver) {
        mDriver = driver;
        return new UpdateDriverFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_update_driver, container, false);
        //init views
        mDriverView = new DriverView(rootView);
        mDriverView.updateOrCreateDriver(mDriver, this);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //init callback
        if (context instanceof OnDriverReadyToSaveListener) {
            mListener = (OnDriverReadyToSaveListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //remove listeners
        mListener = null;
        mDriverView.removerListeners();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_button:
                mDriverView.validateFields(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFieldsValidated(Driver driver) {
        mListener.onDriverReadyToSave(driver);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnDriverReadyToSaveListener {
        void onDriverReadyToSave(Driver driver);
    }
}