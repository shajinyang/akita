package com.sjy.akita;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sjy.akita.databinding.DelegateIndexBinding;
import com.sjy.akita_core.delegate.AkitaDelegate;

import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

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
    }

    public static IndexDelegate create(){

        return new IndexDelegate();
    }

}
