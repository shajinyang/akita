package com.sjy.akita.fragments.webview;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sjy.akita.R;
import com.sjy.akita_core.h5.X5WebView;
import com.tencent.smtt.sdk.WebSettings;

/**
 * Created by sjy on 2018/5/31.
 */

public class X5WebViewActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delegate_webview);
        X5WebView x5WebView= findViewById(R.id.webview);
        x5WebView.loadUrl("https://mp.weixin.qq.com/s/ZQnwppM69NuJoKP4GLNWaw");
    }
}
