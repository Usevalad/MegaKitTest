package com.vsevolod.megakittest.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jrummyapps.android.colorpicker.ColorPickerDialog;
import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.model.Car;
import com.vsevolod.megakittest.view.CarView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCarReadyToSaveListener} interface
 * to handle Car-object saving.
 * {@link ChooseImageCallBack} interface
 * to handle getting image from gallery
 * Use the {@link UpdateCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateCarFragment extends Fragment implements View.OnClickListener,
        CarView.OnFieldsValidatedListener {

    private OnCarReadyToSaveListener mListener;

    private ChooseImageCallBack mChooseImageCallBack;

    /**
     * {@link Car} data-object
     */
    private static Car mCar;

    /**
     * {@link CarView} that will init, keep and manage all views
     * that involved in Car-object rendering
     */
    private CarView mCarView;

    public UpdateCarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UpdateCarFragment.
     */
    public static UpdateCarFragment newInstance(Car car) {
        mCar = car;
        return new UpdateCarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_update_car, container, false);
        //init views
        mCarView = new CarView(rootView, this);
        mCarView.updateOrCreateCar(mCar);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //init callbacks
        if (context instanceof OnCarReadyToSaveListener)
            mListener = (OnCarReadyToSaveListener) context;
        if (context instanceof ChooseImageCallBack)
            mChooseImageCallBack = (ChooseImageCallBack) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //remove listeners
        mListener = null;
        mCarView.removeListeners();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.color_button:
                ColorPickerDialog.newBuilder().setColor(Color.YELLOW).show(getActivity());
                break;
            case R.id.car_add_image_button:
                mChooseImageCallBack.getImageFromGallery();
                break;
            case R.id.submit_button:
                mCarView.validateFields(this);
                break;
            default:
                break;
        }
    }

    public void setPhoto(Uri uri) {
        mCarView.setPhoto(uri);
    }

    public void setColor(int color) {
        mCarView.setButtonColor(color);
    }

    @Override
    public void onFieldsValidated(Car car) {
        mListener.onCarReadyToSave(car);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnCarReadyToSaveListener {
        void onCarReadyToSave(Car car);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface ChooseImageCallBack {
        void getImageFromGallery();
    }
}