/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */
package com.vsevolod.megakittest.adapter;
/**
 * Created by Student Vsevolod on 8/8/17.
 * usevalad.uladzimiravich@gmail.com
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vsevolod.megakittest.constant.Constants;
import com.vsevolod.megakittest.fragment.PlaceholderFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    // FIXME: 8/8/17 add to resources
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case Constants.DATA_TYPE_CAR:
                return "Cars";
            case Constants.DATA_TYPE_DRIVER:
                return "Drivers";
            default:
                return null;
        }
    }
}