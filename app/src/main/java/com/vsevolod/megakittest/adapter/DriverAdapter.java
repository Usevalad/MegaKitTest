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
import android.widget.TextView;

import com.vsevolod.megakittest.R;
import com.vsevolod.megakittest.activity.DriverActivity;
import com.vsevolod.megakittest.constant.IntentKey;
import com.vsevolod.megakittest.model.Driver;

import java.util.List;

/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    public List<Driver> mData;

    public DriverAdapter(Context context, @NonNull List<Driver> data) {
        Log.d(TAG, "MyRecyclerAdapter: constructor");
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public DriverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_view_item_driver, parent, false);
        return new DriverHolder(view);
    }

    @Override
    public void onBindViewHolder(DriverHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        Driver driver = mData.get(position);

        String name;
        name = mContext.getString(R.string.driver_full_name, driver.getFirstName(), driver.getLastName());

        holder.mNameTextView.setText(name);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class DriverHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNameTextView;
        private CardView mDriverCardView;

        private DriverHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.main_driver_name_text_view);
            mDriverCardView = (CardView) itemView.findViewById(R.id.driver_card_view);
            mDriverCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, DriverActivity.class);
            long id = mData.get(getAdapterPosition()).getId();
            intent.putExtra(IntentKey.ACTION, IntentKey.READ);
            intent.putExtra(IntentKey.ID, id);
            mContext.startActivity(intent);
        }
    }
}