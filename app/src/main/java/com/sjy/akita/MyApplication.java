package com.sjy.akita;

import android.app.Application;

import com.sjy.akita_core.Akita;

/**
 * Created by sjy on 2018/5/26.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Akita.init(this)
            .withApiHost("http://datam.zx53.cn/Api2/")
            .config();

    }
}
