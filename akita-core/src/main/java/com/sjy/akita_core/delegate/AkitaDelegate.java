package com.sjy.akita_core.delegate;

import android.databinding.ViewDataBinding;

/**
 * 外部继承基础fragment
 * Created by sjy on 2018/5/25.
 */

public abstract class AkitaDelegate<T extends ViewDataBinding> extends BaseDelegate<T>{

    @Override
    protected void defaultInit() {

    }
}
