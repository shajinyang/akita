package com.sjy.akita_core.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sjy.akita_core.net.callback.IEnd;
import com.sjy.akita_core.net.callback.IError;
import com.sjy.akita_core.net.callback.IStart;
import com.sjy.akita_core.net.callback.ISuccess;

import java.io.File;
import java.util.List;
import java.util.WeakHashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by sjy on 2018/5/26.
 */

public final class RestClient {

    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private final Object JSONPARAMS;
    private final String URL;
    private final File FILE;
    private final ISuccess ISUCCESS;
    private final IError IERROR;
    private final IStart ISTART;
    private final IEnd IEND;
    private final Class<?> CONVERT_BEAN;
    private final Class<?> CONVERT_LIST_BEAN;

    public RestClient(WeakHashMap<String,Object> PARAMS,
                      Object JSONPARAMS,
                      String URL,
                      File file,
                      ISuccess ISUCCESS,
                      IError IERROR,
                      IStart ISTART,IEnd IEND,Class<?> CONVERT_BEAN,Class<?> CONVERT_LIST_BEAN) {
        RestClient.PARAMS.putAll(PARAMS);
        this.JSONPARAMS=JSONPARAMS;
        this.URL=URL;
        this.FILE =file;
        this.ISUCCESS=ISUCCESS;
        this.IERROR=IERROR;
        this.ISTART=ISTART;
        this.IEND=IEND;
        this.CONVERT_BEAN=CONVERT_BEAN;
        this.CONVERT_LIST_BEAN=CONVERT_LIST_BEAN;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    public void get(){
        if(ISTART!=null){
            ISTART.onStart();
        }
        RestCreator.getRestService()
                .get(URL,PARAMS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if(ISUCCESS!=null){
                            if(CONVERT_BEAN!=null){
                                ISUCCESS.onSuccess(JSON.parseObject(s,CONVERT_BEAN));
                            }else if(CONVERT_LIST_BEAN!=null){
                                ISUCCESS.onSuccess(JSON.parseArray(s,CONVERT_LIST_BEAN));
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(IERROR!=null){
                            IERROR.onError(e.getMessage()+"");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(IEND!=null){
                            IEND.onEnd();
                        }
                    }
                });
    }

    public void post(){
        if(ISTART!=null){
            ISTART.onStart();
        }
        RestCreator.getRestService()
                .post(URL,PARAMS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if(ISUCCESS!=null){
                            if(CONVERT_BEAN!=null){
                                ISUCCESS.onSuccess(JSON.parseObject(s,CONVERT_BEAN));
                            }else if(CONVERT_LIST_BEAN!=null){
                                ISUCCESS.onSuccess(JSON.parseArray(s,CONVERT_LIST_BEAN));
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(IERROR!=null){
                            IERROR.onError(e.getMessage()+"");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(IEND!=null){
                            IEND.onEnd();
                        }
                    }
                });
    }

    public void postJson(){
        if(JSONPARAMS==null)return;
        if(ISTART!=null){
            ISTART.onStart();
        }
        String val= JSON.toJSONString(JSONPARAMS);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),val);
        RestCreator.getRestService()
                .postRaw(URL,body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if(ISUCCESS!=null){
                            if(CONVERT_BEAN!=null){
                                ISUCCESS.onSuccess(JSON.parseObject(s,CONVERT_BEAN));
                            }else if(CONVERT_LIST_BEAN!=null){
                                ISUCCESS.onSuccess(JSON.parseArray(s,CONVERT_LIST_BEAN));
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(IERROR!=null){
                            IERROR.onError(e.getMessage()+"");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(IEND!=null){
                            IEND.onEnd();
                        }
                    }
                });
    }

    public void upload(){
        if(ISTART!=null){
            ISTART.onStart();
        }
        final RequestBody requestBody =
                RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
        final MultipartBody.Part body =
                MultipartBody.Part.createFormData("FILE", FILE.getName(), requestBody);
        RestCreator.getRestService()
                .upload(URL,body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if(ISUCCESS!=null){
                            if(CONVERT_BEAN!=null){
                                ISUCCESS.onSuccess(JSON.parseObject(s,CONVERT_BEAN));
                            }else if(CONVERT_LIST_BEAN!=null){
                                ISUCCESS.onSuccess(JSON.parseArray(s,CONVERT_LIST_BEAN));
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(IERROR!=null){
                            IERROR.onError(e.getMessage()+"");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(IEND!=null){
                            IEND.onEnd();
                        }
                    }
                });
    }
}
