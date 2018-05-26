package com.sjy.akita_common.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by sjy on 2018/5/25.
 */

public final class UiUtils {

    /**
     * 隐藏系统状态栏
     * @param activity
     */
    public static void hideBar(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 状态栏透明
     * @param activity
     */
    public static void transparentBar(Activity activity){
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}
