package com.sjy.akita.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sjy.akita.R;
import com.sjy.akita.databinding.DelegateDetailBinding;
import com.sjy.akita_core.delegate.AkitaSwipeBackDelegate;

/**
 * Created by sjy on 2018/5/25.
 */

public class DetailSwipeBackDelegate extends AkitaSwipeBackDelegate<DelegateDetailBinding> {

    @Override
    public Object setLayout() {
        return R.layout.delegate_detail;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        v.tvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(IndexSwipeBackDelegate.create());
            }
        });
    }

    public static DetailSwipeBackDelegate create(){
        return new DetailSwipeBackDelegate();
    }

}
