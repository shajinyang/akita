package com.sjy.akita;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.sjy.akita.databinding.DelegateIndexBinding;
import com.sjy.akita_core.delegate.AkitaDelegate;
import com.sjy.akita_core.log.AkitaLog;

import java.util.ArrayList;

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
        ArrayList<String> list=new ArrayList<>();
        list.add("站桑电视上");
        list.add("的上升空间");
        list.add("滑动时抠脚大汉");
        list.add("符合贷款");
        list.add("近代史可");
        list.add("会打瞌睡");
        AkitaLog.d("hehe",list);
    }

    public static IndexDelegate create(){

        return new IndexDelegate();
    }

}
