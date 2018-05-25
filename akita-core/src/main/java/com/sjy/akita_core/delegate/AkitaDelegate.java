package com.sjy.akita_core.delegate;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sjy on 2018/5/25.
 */

public abstract class AkitaDelegate extends BaseDelegate{

    public abstract  <T extends ViewDataBinding> T getBinding();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=getBinding();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
