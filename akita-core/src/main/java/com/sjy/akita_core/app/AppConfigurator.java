package com.sjy.akita_core.app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Interceptor;

/**
 * akita框架全局配置
 * Created by sjy on 2018/5/26.
 */

public final class AppConfigurator {


    private static final HashMap<Object,Object> AKITA_CONFIGS=new HashMap<>();
    private static final HashSet<Interceptor> INTERCEPTORS=new HashSet<>();

    private AppConfigurator() {
        AKITA_CONFIGS.put(ConfigKeys.CONFIG_READY,false);
    }

    private static class Holder{
        private static final AppConfigurator instance=new AppConfigurator();
    }

    public static AppConfigurator getInstance(){
        return Holder.instance;
    }


    public final AppConfigurator withApiHost(String host){
        AKITA_CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }

    public final AppConfigurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        AKITA_CONFIGS.put(ConfigKeys.INTERCEPT,INTERCEPTORS);
        return this;
    }

    public final void config(){
        AKITA_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) AKITA_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    public <T> T getConfig(Object key){
        checkConfiguration();
        return (T) AKITA_CONFIGS.get(key);
    }




}
