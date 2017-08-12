/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.activity.CarActivity;
import com.vsevolod.megakittest.constant.Constants;
import com.vsevolod.megakittest.constant.IntentKey;
import com.vsevolod.megakittest.model.Car;
import com.vsevolod.megakittest.util.PicassoClient;

import java.util.List;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {

    private Context mContext;

    /**
     * A {@link Car} list of data-objects
     */
    private List<Car> mData;

    /***
     * A {@link DeleteCarCallback} callback to delete data-object from db
     */
    private DeleteCarCallback mDeleteCarCallback;

    public CarAdapter(Context context, List<Car> data, DeleteCarCallback deleteCarCallback) {
        this.mContext = context;
        this.mData = data;
        this.mDeleteCarCallback = deleteCarCallback;
    }

    @Override
    public CarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_view_item_car, parent, false);
        return new CarHolder(view);
    }

    @Override
    public void onBindViewHolder(CarHolder holder, final int position) {
        Car car = mData.get(position);
        //get strings from resources to put into TextViews
        String bodyType, model, manufacturer;
        bodyType = mContext.getString(R.string.main_car_body_type, car.getBodyType());
        model = mContext.getString(R.string.main_car_model, car.getModel());
        manufacturer = mContext.getString(R.string.main_car_manufacturer, car.getManufacturer());
        //set strings
        holder.mBodyTypeTextView.setText(bodyType);
        holder.mModelTextView.setText(model);
        holder.mManufacturerTextView.setText(manufacturer);
        //set image
        PicassoClient.downloadToAdapter(mContext, car.getUri(), holder.mCarImageView);

    }

    @Override
    public void onViewRecycled(CarHolder holder) {
        holder.itemView.setOnCreateContextMenuListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    class CarHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        private ImageView mCarImageView;
        private TextView mModelTextView;
        private TextView mManufacturerTextView;
        private TextView mBodyTypeTextView;
        private CardView mCarCardView;

        private CarHolder(View itemView) {
            super(itemView);
            //init views
            mModelTextView = (TextView) itemView.findViewById(R.id.main_car_model_text_view);
            mManufacturerTextView = (TextView) itemView.findViewById(R.id.main_car_manufacturer_text_view);
            mBodyTypeTextView = (TextView) itemView.findViewById(R.id.main_car_body_type_text_view);
            mCarImageView = (ImageView) itemView.findViewById(R.id.main_car_image_view);
            mCarCardView = (CardView) itemView.findViewById(R.id.car_card_view);
            //set listeners
            mCarCardView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            startCarActivity(IntentKey.ACTION_READ);
        }

        /**
         * Start new CarActivity with fragment depends on action
         *
         * @param action - string to start one of CarActivity fragments
         */
        private void startCarActivity(String action) {
            Intent intent = new Intent(mContext, CarActivity.class);
            String id = mData.get(getAdapterPosition()).getId();
            intent.putExtra(IntentKey.ACTION, action);
            intent.putExtra(IntentKey.ID, id);
            mContext.startActivity(intent);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            //get titles
            String edit = mContext.getString(R.string.edit);
            String delete = mContext.getString(R.string.delete);
            //set menu items
            MenuItem Edit = menu.add(Menu.NONE, Constants.EDIT_ID, Constants.EDIT_ORDER, edit);
            MenuItem Delete = menu.add(Menu.NONE, Constants.DELETE_ID, Constants.DELETE_ORDER, delete);
            //set onClickListener
            Edit.setOnMenuItemClickListener(this);
            Delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case Constants.EDIT_ID:
                    startCarActivity(IntentKey.ACTION_UPDATE);
                    return true;
                case Constants.DELETE_ID:
                    removeItem();
                    return true;
                default:
                    return false;
            }
        }

        /**
         * remove data-object from db
         * update view
         */
        private void removeItem() {
            int position = getAdapterPosition();
            mDeleteCarCallback.deleteCar(mData.get(position));
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mData.size());
        }
    }

    /**
     * Callback to trigger Activity for removing data-object
     */
    public interface DeleteCarCallback {
        void deleteCar(Car car);
    }
}