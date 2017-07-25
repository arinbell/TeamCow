package com.teamcow.wheresmystuff.controller;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.teamcow.wheresmystuff.R;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by arinb on 7/25/2017.
 */

public class ImageHandler {
    public static Drawable loadImageFromURL(Context context, String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            Drawable d = ContextCompat.getDrawable(context, R.drawable.logo_white);
            return d;
        }
    }
}
