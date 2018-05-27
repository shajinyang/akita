package com.sjy.akita;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.sjy.akita.beans.ResBean;
import com.sjy.akita.databinding.DelegateIndexBinding;
import com.sjy.akita_core.delegate.AkitaDelegate;
import com.sjy.akita_core.log.AkitaLog;
import com.sjy.akita_core.net.RestClient;
import com.sjy.akita_core.net.callback.IError;
import com.sjy.akita_core.net.callback.IRequest;
import com.sjy.akita_core.net.callback.ISuccess;

import java.util.ArrayList;


/**
 * Created by sjy on 2018/5/25.
 */

public class IndexDelegate extends AkitaDelegate<DelegateIndexBinding> {

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        v.tvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().start(DetailDelegate.create());
            }
        });
        ArrayList<String> list=new ArrayList<>();
        list.add("站桑电视上");
        list.add("的上升空间");
        list.add("滑动时抠脚大汉");
        list.add("符合贷款");
        list.add("近代史可");
        list.add("会打瞌睡");
        AkitaLog.d("hehe",list);
        testNet();

    }

    private void testNet(){
        RestClient.builder()
                .url("Friends/typeList")
                .params("a","b")
                .convert(ResBean.class)
                .requset(new IRequest() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onEnd() {

                    }
                })
                .success(new ISuccess<ResBean>() {
                    @Override
                    public void onSuccess(ResBean resBean) {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(String msg) {

                    }
                })
                .build()
                .get();
    }

    public static IndexDelegate create(){

        return new IndexDelegate();
    }

}
