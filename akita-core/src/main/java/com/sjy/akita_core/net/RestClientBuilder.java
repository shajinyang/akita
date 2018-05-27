package com.sjy.akita_core.net;

import com.sjy.akita_core.net.callback.IError;
import com.sjy.akita_core.net.callback.IRequest;
import com.sjy.akita_core.net.callback.ISuccess;

import java.io.File;
import java.util.WeakHashMap;

/**
 * Created by sjy on 2018/5/26.
 */

public class RestClientBuilder {

    private  final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private  String URL="";
    private  File file=null;
    private  ISuccess ISUCCESS=null;
    private  IError IERROR=null;
    private  IRequest IREQUEST=null;
    private  Class<?> CONVERT_BEAN=null;


    public RestClientBuilder params(String key,Object value){
        PARAMS.put(key,value);
        return this;
    }

    public RestClientBuilder url(String url){
        URL=url;
        return this;
    }

    public RestClientBuilder file(File file){
        this.file=file;
        return this;
    }

    public RestClientBuilder file(String filePath){
        this.file=new File(filePath);
        return this;
    }

    public RestClientBuilder error(IError iError){
        IERROR=iError;
        return this;
    }

    public RestClientBuilder success(ISuccess iSuccess){
        ISUCCESS=iSuccess;
        return this;
    }

    public RestClientBuilder requset(IRequest irequest){
        IREQUEST=irequest;
        return this;
    }
    public RestClientBuilder convert(Class<?> CONVERT_BEAN){
        this.CONVERT_BEAN=CONVERT_BEAN;
        return this;
    }

    public RestClient build(){
        return new RestClient(PARAMS,URL,file,ISUCCESS,IERROR,IREQUEST,CONVERT_BEAN);
    }

}
