/*
 * Copyright (c) 2017.
 *  Student Vsevolod
 *  usevalad.uladzimiravich@gmail.com
 */

package com.vsevolod.megakittest.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;

/**
 * Created by Student Vsevolod on 8/10/17.
 * usevalad.uladzimiravich@gmail.com
 */

public class SubmitButtonView {
    private Button mSubmitButton;

    public SubmitButtonView(View rootView) {
        Context context = rootView.getContext();
//        mSubmitButton = (Button) rootView.findViewById(R.id.submit_button);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mSubmitButton.setOnClickListener(onClickListener);
    }

    public void removeOnClickListener() {
        mSubmitButton.setOnClickListener(null);
    }
}