package com.sjy.akita_core;

import android.app.Application;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sjy.akita_core.app.AppConfigurator;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;


/**
 * Created by sjy on 2018/5/5.
 */

public final class Akita {


    public static AppConfigurator init(Application application){
        initLogger();
        initX5WebView(application);
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
     * 内存泄露监测
     * @param application
     */
    public static void openLeakCanary(Application application){
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(application);
    }



    /**
     * 初始化日志打印
     */
    private static void initLogger(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 初始化X5weview内核
     * @param application
     */
    private static void initX5WebView(Application application) {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app_X5_webview", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(application.getApplicationContext(), cb);
    }

}
