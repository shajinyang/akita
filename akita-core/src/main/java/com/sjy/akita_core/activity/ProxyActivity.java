package com.sjy.akita_core.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;

import com.sjy.akita_core.R;
import com.sjy.akita_core.delegate.AkitaDelegate;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by sjy on 2018/5/25.
 */

public abstract class ProxyActivity extends AppCompatActivity implements ISupportActivity{


    private final SupportActivityDelegate DELEGATE=new SupportActivityDelegate(this);

    public abstract AkitaDelegate setRootDelegate();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DELEGATE.onDestroy();
        System.gc();
        System.runFinalization();
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout contentFrameLayout=new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.akita_delegate_id);
        setContentView(contentFrameLayout);
        if(savedInstanceState==null){
            DELEGATE.loadRootFragment(R.id.akita_delegate_id,setRootDelegate());
        }
    }

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public void post(Runnable runnable) {
        DELEGATE.post(runnable);
    }

    @Override
    public void onBackPressedSupport() {
        DELEGATE.onBackPressedSupport();
    }
}
