package com.sjy.akita_core.net;

import com.alibaba.fastjson.JSON;
import com.sjy.akita_core.net.callback.IError;
import com.sjy.akita_core.net.callback.IRequest;
import com.sjy.akita_core.net.callback.ISuccess;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by sjy on 2018/5/26.
 */

public final class RestClient {

    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private final String URL;
    private final File FILE;
    private final ISuccess ISUCCESS;
    private final IError IERROR;
    private final IRequest IREQUEST;
    private final Class<?> CONVERT_BEAN;


    public RestClient(WeakHashMap<String,Object> PARAMS,
                      String URL,
                      File file,
                      ISuccess ISUCCESS,
                      IError IERROR,
                      IRequest IREQUEST,Class<?> CONVERT_BEAN) {
        RestClient.PARAMS.putAll(PARAMS);
        this.URL=URL;
        this.FILE =file;
        this.ISUCCESS=ISUCCESS;
        this.IERROR=IERROR;
        this.IREQUEST=IREQUEST;
        this.CONVERT_BEAN=CONVERT_BEAN;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    public void get(){
        if(IREQUEST!=null){
            IREQUEST.onStart();
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
                           ISUCCESS.onSuccess(JSON.parseObject(s,CONVERT_BEAN));
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
                        if(IREQUEST!=null){
                            IREQUEST.onEnd();
                        }
                    }
                });
    }

    public void post(){
        if(IREQUEST!=null){
            IREQUEST.onStart();
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
                            ISUCCESS.onSuccess(JSON.parseObject(s,CONVERT_BEAN));
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
                        if(IREQUEST!=null){
                            IREQUEST.onEnd();
                        }
                    }
                });
    }

    public void upload(){
        if(IREQUEST!=null){
            IREQUEST.onStart();
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
                            ISUCCESS.onSuccess(JSON.parseObject(s,CONVERT_BEAN));
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
                        if(IREQUEST!=null){
                            IREQUEST.onEnd();
                        }
                    }
                });
    }
}
