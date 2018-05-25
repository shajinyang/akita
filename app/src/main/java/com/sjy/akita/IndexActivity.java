package com.sjy.akita;

import com.sjy.akita_core.activity.AkitaActivity;
import com.sjy.akita_core.activity.AkitaSwipeBackActivity;
import com.sjy.akita_core.delegate.AkitaDelegate;
import com.sjy.akita_core.delegate.AkitaSwipeBackDelegate;


/**
 * Created by sjy on 2018/5/25.
 */

public class IndexActivity extends AkitaSwipeBackActivity {


    @Override
    public AkitaSwipeBackDelegate setRootDelegate() {
        return IndexSwipeBackDelegate.create();
    }
}
