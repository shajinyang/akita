package com.sjy.akita;


import com.sjy.akita.fragments.MainDelegate;
import com.sjy.akita_common.utils.UiUtils;
import com.sjy.akita_core.activity.AkitaActivity;
import com.sjy.akita_core.delegate.AkitaDelegate;

/**
 * Created by sjy on 2018/5/25.
 */

public class MainActivity extends AkitaActivity {

    @Override
    public AkitaDelegate setRootDelegate() {
        UiUtils.transparentBar(this);
        return MainDelegate.create();
    }

}
