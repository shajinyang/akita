package com.sjy.akita_core;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sjy.akita_core.app.AppConfigurator;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by sjy on 2018/5/5.
 */

public final class Akita {

    public static AppConfigurator init(Application application){
        initLogger();
        initLeakCanary(application);
        return AppConfigurator.getInstance();
    }

    /**
     * 读取配置信息
     * @return
     */
    public static <T> T getConfigs(Object key){
        return AppConfigurator.getInstance().getConfig(key);
    }

    /**
     * 初始化日志打印
     */
    private static void initLogger(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 初始化内存泄露监测
     * @param application
     */
    private static void initLeakCanary(Application application){
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(application);
    }

}
