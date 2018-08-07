package com.sjy.akita_core.net;

import com.sjy.akita_core.net.callback.IEnd;
import com.sjy.akita_core.net.callback.IError;
import com.sjy.akita_core.net.callback.IStart;
import com.sjy.akita_core.net.callback.ISuccess;

import java.io.File;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by sjy on 2018/5/26.
 */

public class RestClientBuilder {

    private  final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private Object JSONPARAMS=null;
    private Object OBJECTPARAM=null;
    private  String URL="";
    private  File file=null;
    private  ISuccess ISUCCESS=null;
    private  IError IERROR=null;
    private IStart ISTART=null;
    private IEnd IEND=null;
    private  Class<?> CONVERT_BEAN=null;
    private  Class<?> CONVERT_LIST_BEAN=null;


    public RestClientBuilder params(String key,Object value){
        PARAMS.put(key,value);
        return this;
    }
    public RestClientBuilder jsonParams(Object pams){
        JSONPARAMS=pams;
        return this;
    }

    public RestClientBuilder objParam(Object param){
        OBJECTPARAM=param;
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

    public RestClientBuilder start(IStart iStart){
        ISTART=iStart;
        return this;
    }
    public RestClientBuilder end(IEnd iEnd){
        IEND=iEnd;
        return this;
    }
    public RestClientBuilder convert(Class<?> CONVERT_BEAN){
        this.CONVERT_BEAN=CONVERT_BEAN;
        return this;
    }
    public RestClientBuilder convertList(Class<?> CONVERT_LIST_BEAN){
        this.CONVERT_LIST_BEAN=CONVERT_LIST_BEAN;
        return this;
    }

    public RestClient build(){
        return new RestClient(PARAMS,JSONPARAMS,OBJECTPARAM,URL,file,ISUCCESS,IERROR,ISTART,IEND,CONVERT_BEAN,CONVERT_LIST_BEAN);
    }

}
