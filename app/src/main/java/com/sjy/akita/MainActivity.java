package com.sjy.akita;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sjy.akita_core.activity.AkitaActivity;
import com.sjy.akita_core.delegate.AkitaDelegate;

/**
 * Created by sjy on 2018/5/25.
 */

public class MainActivity extends AkitaActivity {

    @Override
    public AkitaDelegate setRootDelegate() {
        return MainDelegate.create();

    }

}
