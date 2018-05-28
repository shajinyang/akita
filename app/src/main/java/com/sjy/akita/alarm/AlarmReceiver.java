package com.sjy.akita.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.sjy.akita_core.log.AkitaLog;

/**
 * Created by sjy on 2018/5/28.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"闹钟来了",Toast.LENGTH_SHORT).show();
        AkitaLog.e("闹钟启动");
    }
}
