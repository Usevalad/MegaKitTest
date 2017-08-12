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
import android.widget.TextView;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.activity.DriverActivity;
import com.vsevolod.megakittest.constant.Constants;
import com.vsevolod.megakittest.constant.IntentKey;
import com.vsevolod.megakittest.model.Driver;

import java.util.List;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverHolder> {
    private Context mContext;

    /**
     * A {@link Driver} list of data-objects
     */
    private List<Driver> mData;

    /***
     * A {@link DriverAdapter.Callback} callback to delete data-object from db
     */

    private DriverAdapter.Callback mCallback;

    public DriverAdapter(Context context, List<Driver> drivers, DriverAdapter.Callback callback) {
        this.mContext = context;
        this.mData = drivers;
        this.mCallback = callback;
    }

    @Override
    public DriverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_view_item_driver, parent, false);
        return new DriverHolder(view);
    }

    @Override
    public void onBindViewHolder(DriverHolder holder, int position) {
        Driver driver = mData.get(position);

        //get string from resources
        String name;
        name = mContext.getString(R.string.driver_full_name, driver.getFirstName(), driver.getLastName());
        //set strings into TextViews
        holder.mNameTextView.setText(name);
        holder.mPhoneNumberTextView.setText(driver.getPhoneNumber());
    }

    @Override
    public void onViewRecycled(DriverHolder holder) {
        holder.itemView.setOnCreateContextMenuListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    class DriverHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        private TextView mNameTextView;
        private TextView mPhoneNumberTextView;
        private CardView mDriverCardView;

        private DriverHolder(View itemView) {
            super(itemView);
            //init views
            mNameTextView = (TextView) itemView.findViewById(R.id.main_driver_name_text_view);
            mPhoneNumberTextView = (TextView) itemView.findViewById(R.id.main_driver_phone_number_text_view);
            mDriverCardView = (CardView) itemView.findViewById(R.id.driver_card_view);
            //set listeners
            mDriverCardView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            startDriverActivity(IntentKey.ACTION_READ);
        }

        /**
         * Start new DriverActivity with fragment depends on action
         *
         * @param action - string to start one of CarActivity fragments
         */
        private void startDriverActivity(String action) {
            Intent intent = new Intent(mContext, DriverActivity.class);
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
            MenuItem Edit = menu.add(Menu.NONE, Constants.EDIT_ID, Constants.DELETE_ORDER, edit);
            MenuItem Delete = menu.add(Menu.NONE, Constants.DELETE_ID, Constants.DELETE_ORDER, delete);
            //set onClickListener
            Edit.setOnMenuItemClickListener(this);
            Delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case Constants.EDIT_ID:
                    startDriverActivity(IntentKey.ACTION_UPDATE);
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
            mCallback.deleteDriver(mData.get(position));
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mData.size());
        }
    }

    /**
     * Callback to trigger Activity for removing data-object
     */
    public interface Callback {
        void deleteDriver(Driver driver);
    }
}