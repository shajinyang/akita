package com.sjy.akita;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sjy.akita.databinding.DelegateMainBinding;
import com.sjy.akita_core.delegate.AkitaDelegate;
import com.sjy.akita_ui.alertpicker.AlertPicker;
import com.sjy.akita_ui.alertpicker.callback.IOnSelect;

/**
 * Created by sjy on 2018/5/25.
 */

public class MainDelegate extends AkitaDelegate<DelegateMainBinding> {
    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    public static MainDelegate create(){
        return new MainDelegate();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        v.tvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(IndexDelegate.create());
            }
        });

        v.tvBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(_mActivity,IndexActivity.class));
            }
        });
        v.tvBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertPicker.create(_mActivity)
                        .onSelect(new IOnSelect() {
                            @Override
                            public void onSelect(String position1, String position2, String position3) {

                            }
                        }).show();
            }
        });

    }
}
