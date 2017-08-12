package com.vsevolod.megakittest.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vsevolod.megakittest.R;

/**
 * Created by Student Vsevolod on 8/12/17.
 * usevalad.uladzimiravich@gmail.com
 * <p>
 * Sipmle class to use Picasso
 */

public final class PicassoClient {

    /**
     * set image to ImageView and resize it to improve performance
     *
     * @param context   - Picasso need context
     * @param path      -image path
     * @param imageView - ImageView to hold image
     */
    public static void downloadToAdapter(Context context, String path, ImageView imageView) {
        //approximate size
        final int HEIGHT = 200;
        final int WIDTH = 400;

        if (!TextUtils.isEmpty(path)) {
            Picasso.with(context)
                    .load(path)
                    .placeholder(R.drawable.tesla)
                    .resize(WIDTH, HEIGHT)
                    .centerCrop()
                    .into(imageView);
        } else {
            Picasso.with(context)
                    .load(R.drawable.tesla)
                    .resize(WIDTH, HEIGHT)
                    .centerCrop()
                    .into(imageView);
        }
    }

    /**
     * set image to ImageView
     *
     * @param context   - Picasso need context
     * @param path      -image path
     * @param imageView - ImageView to hold image
     */
    public static void downloadImage(Context context, String path, ImageView imageView) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(context)
                    .load(path)
                    .placeholder(R.drawable.tesla)
                    .into(imageView);
        } else {
            Picasso.with(context)
                    .load(R.drawable.tesla)
                    .into(imageView);
        }
    }
}