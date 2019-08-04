package com.horizon.app.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.horizon.app.core.app.Horizon;

//用于测量，计算宽高
public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Horizon.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Horizon.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
