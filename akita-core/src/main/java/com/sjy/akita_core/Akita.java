package com.sjy.akita_core;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by sjy on 2018/5/5.
 */

public final class Akita {

    public static final void init(Application application){
        initLogger();
        initLeakCanary(application);
    }

    /**
     * 初始化日志打印
     */
    private static final void initLogger(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 初始化内存泄露监测
     * @param application
     */
    private static final void initLeakCanary(Application application){
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(application);
    }

}
