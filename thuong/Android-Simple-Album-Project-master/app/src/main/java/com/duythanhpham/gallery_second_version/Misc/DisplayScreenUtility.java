package com.duythanhpham.gallery_second_version.Misc;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.support.annotation.NonNull;

public class DisplayScreenUtility {
    public static int getScreenWidth(@NonNull Context context) {
        Point size = new Point();
        ((Activity) context).getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    public static boolean isInLandscapeMode(@NonNull Context context) {
        boolean isLandscape = false;
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandscape = true;
        }
        return isLandscape;
    }
}
