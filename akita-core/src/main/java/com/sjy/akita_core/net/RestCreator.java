package com.sjy.akita_core.net;

import com.sjy.akita_core.Akita;
import com.sjy.akita_core.app.ConfigKeys;

import java.util.HashSet;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by sjy on 2018/5/26.
 */

public final class RestCreator {


    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }


    /**
     * 构建OkhttpClient
     */
    public static final class OkHttpHolder{
        private static final int TIME_OUT=60;
        private static final OkHttpClient.Builder BUILDER=new OkHttpClient.Builder();
        private static final HashSet<Interceptor> INTERCEPTORS=Akita.getConfigs(ConfigKeys.INTERCEPT);
        private static final HashSet<Interceptor> NETWORK_INTERCEPTORS=Akita.getConfigs(ConfigKeys.NETWORK_INTERCEPT);
        private static OkHttpClient.Builder addInterceptor(){
            if(INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()) {
                for (Interceptor in : INTERCEPTORS
                        ) {
                    BUILDER.addInterceptor(in);
                }
            }
            if(NETWORK_INTERCEPTORS!=null&&!NETWORK_INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor:NETWORK_INTERCEPTORS){
                    BUILDER.addNetworkInterceptor(interceptor);
                }
            }

            return BUILDER;
        }
        private static final OkHttpClient OK_HTTP_CLIENT=addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

    }

    /**
     * 构建retrofitclient
     */
    public static final class RetrofitClientHolder{
        private static final String BASE_URL = Akita.getConfigs(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 构建service
     */
    public static final class ServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitClientHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return ServiceHolder.REST_SERVICE;
    }

}
