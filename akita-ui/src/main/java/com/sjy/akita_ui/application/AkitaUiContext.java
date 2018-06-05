package com.sjy.akita_ui.application;

import android.app.Application;

/**
 * Created by sjy on 2018/6/5.
 */

public class AkitaUiContext {

    public static Application application;
    public static void init(Application application){
        AkitaUiContext.application=application;
    }
}
