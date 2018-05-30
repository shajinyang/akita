package com.sjy.akita.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.sjy.akita.R;
import com.sjy.akita.databinding.DelegatePopBinding;
import com.sjy.akita_core.delegate.AkitaDelegate;
import com.sjy.akita_core.log.AkitaLog;
import com.sjy.akita_ui.poper.PopOrientation;
import com.sjy.akita_ui.poper.Poper;

/**
 * Created by sjy on 2018/5/29.
 */

public class PopDelegate extends AkitaDelegate<DelegatePopBinding> {
    Poper poper1;
    Poper poper2;
    Poper poper3;
    Poper poper4;
    @Override
    protected Object setLayout() {
        return R.layout.delegate_pop;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        v.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view= LayoutInflater.from(_mActivity)
                        .inflate(R.layout.pop_item,null);
                if(poper1==null){
                    poper1=Poper.builder()
                            .setAnchorView(v)
                            .setIsTouchOutCancelable(false)
                            .setOrientation(PopOrientation.FROM_TOP)
                            .setPopView(view)
                            .build();
                }
                poper1.show();

            }
        });

        v.left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view= LayoutInflater.from(_mActivity)
                        .inflate(R.layout.pop_item,null);
                if(poper2==null){
                    poper2=Poper.builder()
                            .setAnchorView(v)
                            .setIsTouchOutCancelable(false)
                            .setOrientation(PopOrientation.FROM_LEFT)
                            .setPopView(view)
                            .build();
                }
                poper2.show();
            }
        });

        v.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view= LayoutInflater.from(_mActivity)
                        .inflate(R.layout.pop_item,null);
                if(poper3==null){
                    poper3= Poper.builder()
                            .setIsTouchOutCancelable(false)
                            .setOrientation(PopOrientation.FROM_BOTTOM)
                            .setPopView(view)
                            .build();
                }
               poper3.show();
            }
        });

        v.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view= LayoutInflater.from(_mActivity)
                        .inflate(R.layout.pop_item,null);
                if(poper4==null){
                    poper4=Poper.builder()
                            .setAnchorView(v)
                            .setIsTouchOutCancelable(false)
                            .setOrientation(PopOrientation.FROM_RIGHT)
                            .setPopView(view)
                            .build();
                }
                poper4.show();
            }
        });
    }
}
