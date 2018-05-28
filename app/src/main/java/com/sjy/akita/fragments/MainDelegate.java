package com.sjy.akita.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sjy.akita.IndexActivity;
import com.sjy.akita.R;
import com.sjy.akita.databinding.DelegateMainBinding;
import com.sjy.akita_core.delegate.AkitaDelegate;
import com.sjy.akita_ui.alertpicker.AlertPicker;
import com.sjy.akita_ui.alertpicker.PickerType;
import com.sjy.akita_ui.alertpicker.callback.IOnSelect;

/**
 * Created by sjy on 2018/5/25.
 */

public class MainDelegate extends AkitaDelegate<DelegateMainBinding> {
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    public static MainDelegate create(){
        return new MainDelegate();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        v.tvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(IndexDelegate.create());
            }
        });

        v.tvBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(_mActivity,IndexActivity.class));
            }
        });
        v.tvBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertPicker.create(_mActivity)
                        .type(PickerType.PROVINCE_CITY)
                        .onSelect(new IOnSelect() {
                            @Override
                            public void onSelect(String position1, String position2, String position3) {

                            }
                        }).show();
            }
        });
        v.tvBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               start(new RecycleviewDelegate());
            }
        });
        v.tvBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(new MultiRecycleviewDelegate());
            }
        });

        /*Intent intent = new Intent(_mActivity,MainActivity.class);
        intent.setAction("hehe");

        pendingIntent = PendingIntent.getActivity(_mActivity, 0, intent, 0);
        alarmManager= (AlarmManager) _mActivity.getSystemService(Context.ALARM_SERVICE);
        int offset= 10 * 1000;//间隔时间10s
        long triggerAtTime = SystemClock.elapsedRealtime() + offset;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AkitaLog.e("setExactAndAllowWhileIdle广播发送");
            alarmManager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AkitaLog.e("setExact广播发送");
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        } else {
            AkitaLog.e("setRepeating广播发送");
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime, offset, pendingIntent);
        }*/

    }

}
