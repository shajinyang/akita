package com.sjy.akita_core.delegate;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by sjy on 2018/5/25.
 */

public abstract class BaseSwipeBackDelegate<T extends ViewDataBinding> extends SwipeBackFragment {
    protected T v;
    protected FragmentActivity _mactivity=null;
    protected abstract Object setLayout();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _mactivity=getActivity();
    }

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
        return attachToSwipeBack(v.getRoot());
    }
}
