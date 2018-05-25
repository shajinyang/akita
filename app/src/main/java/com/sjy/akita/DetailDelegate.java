package com.sjy.akita;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sjy.akita.databinding.DelegateDetailBinding;
import com.sjy.akita.databinding.DelegateIndexBinding;
import com.sjy.akita_core.delegate.AkitaDelegate;

import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

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
