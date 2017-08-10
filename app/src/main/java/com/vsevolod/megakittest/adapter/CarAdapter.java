/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.Repository;
import com.vsevolod.megakittest.activity.CarActivity;
import com.vsevolod.megakittest.constant.IntentKey;
import com.vsevolod.megakittest.model.Car;

import java.util.List;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private List<Car> mData;

    public CarAdapter(Context context, @NonNull List<Car> data) {
        Log.d(TAG, "MyRecyclerAdapter: constructor");
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public CarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_view_item_car, parent, false);
        return new CarHolder(view);
    }

    @Override
    public void onBindViewHolder(CarHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        Car car = mData.get(position);

        String bodyType, model, manufacturer;
        bodyType = mContext.getString(R.string.car_body_type, car.getBodyType());
        model = mContext.getString(R.string.car_model, car.getModel());
        manufacturer = mContext.getString(R.string.car_manufacturer, car.getManufacturer());

        holder.mBodyTypeTextView.setText(bodyType);
        holder.mModelTextView.setText(model);
        holder.mManufacturerTextView.setText(manufacturer);
        Picasso.with(mContext)
                .load(Repository.getCarPhoto())
                .into(holder.mCarImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class CarHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mCarImageView;
        private TextView mModelTextView;
        private TextView mManufacturerTextView;
        private TextView mBodyTypeTextView;
        private CardView mCarCardView;

        private CarHolder(View itemView) {
            super(itemView);
            mModelTextView = (TextView) itemView.findViewById(R.id.main_car_model_text_view);
            mManufacturerTextView = (TextView) itemView.findViewById(R.id.main_car_manufacturer_text_view);
            mBodyTypeTextView = (TextView) itemView.findViewById(R.id.main_car_body_type_text_view);
            mCarImageView = (ImageView) itemView.findViewById(R.id.main_car_image_view);
            mCarCardView = (CardView) itemView.findViewById(R.id.car_card_view);
            mCarCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, CarActivity.class);
            long id = mData.get(getAdapterPosition()).getId();
            intent.putExtra(IntentKey.ACTION, IntentKey.READ);
            intent.putExtra(IntentKey.ID, id);
            mContext.startActivity(intent);
        }
    }
}