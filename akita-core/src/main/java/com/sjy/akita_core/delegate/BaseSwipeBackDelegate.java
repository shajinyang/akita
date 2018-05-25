package com.sjy.akita_core.delegate;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackFragment;
import me.yokeyword.fragmentation_swipeback.core.SwipeBackFragmentDelegate;

/**
 * Created by sjy on 2018/5/24.
 */

public abstract class BaseSwipeBackDelegate<T extends ViewDataBinding> extends Fragment implements ISwipeBackFragment {

    public T v;
    protected FragmentActivity _mactivity=null;
    public abstract Object setLayout();
    private final SwipeBackFragmentDelegate DELEGATE=new SwipeBackFragmentDelegate(this);



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(setLayout() instanceof Integer){
            v= DataBindingUtil.inflate(inflater, (Integer) setLayout(),container,false);
        }else if(setLayout() instanceof View){
            v=DataBindingUtil.findBinding((View) setLayout());
        }else {
            throw  new ClassCastException("setLayout() must return Integer or View");
        }
        return v.getRoot();

    }






    //接口实现

    @Override
    public View attachToSwipeBack(View view) {
        return DELEGATE.attachToSwipeBack(view);
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return DELEGATE.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        DELEGATE.setSwipeBackEnable(enable);
    }

    @Override
    public void setEdgeLevel(SwipeBackLayout.EdgeLevel edgeLevel) {
        DELEGATE.setEdgeLevel(edgeLevel);
    }

    @Override
    public void setEdgeLevel(int widthPixel) {
        DELEGATE.setEdgeLevel(widthPixel);
    }

    @Override
    public void setParallaxOffset(float offset) {
        DELEGATE.setParallaxOffset(offset);
    }
}
