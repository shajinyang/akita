package com.sjy.akita.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sjy.akita.R;
import com.sjy.akita.databinding.DelegateDetailBinding;
import com.sjy.akita_core.delegate.AkitaDelegate;

/**
 * Created by sjy on 2018/5/25.
 */

public class DetailDelegate extends AkitaDelegate<DelegateDetailBinding> {

    @Override
    public Object setLayout() {
        return R.layout.delegate_detail;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    public static DetailDelegate create(){
        return new DetailDelegate();
    }

}
