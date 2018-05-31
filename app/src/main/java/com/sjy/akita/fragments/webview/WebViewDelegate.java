package com.sjy.akita.fragments.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sjy.akita.R;
import com.sjy.akita.databinding.DelegateWebview2Binding;
import com.sjy.akita_core.delegate.AkitaDelegate;

/**
 * Created by sjy on 2018/5/31.
 */

public class WebViewDelegate extends AkitaDelegate<DelegateWebview2Binding> {
    @Override
    protected Object setLayout() {
        return R.layout.delegate_webview2;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        v.webview.getSettings().setJavaScriptEnabled(true);
        v.webview.loadUrl("https://mp.weixin.qq.com/s/ZQnwppM69NuJoKP4GLNWaw");
    }
}
