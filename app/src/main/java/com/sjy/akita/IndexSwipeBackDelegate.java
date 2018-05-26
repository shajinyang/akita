package com.sjy.akita;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sjy.akita.databinding.DelegateIndexBinding;
import com.sjy.akita_common.utils.UiUtils;
import com.sjy.akita_core.delegate.AkitaDelegate;
import com.sjy.akita_core.delegate.AkitaSwipeBackDelegate;

/**
 * Created by sjy on 2018/5/25.
 */

public class IndexSwipeBackDelegate extends AkitaSwipeBackDelegate<DelegateIndexBinding> {

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
                start(DetailSwipeBackDelegate.create());
            }
        });
    }

    public static IndexSwipeBackDelegate create(){

        return new IndexSwipeBackDelegate();
    }

}
