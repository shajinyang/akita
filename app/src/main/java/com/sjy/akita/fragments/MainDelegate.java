package com.sjy.akita.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.sjy.akita.IndexActivity;
import com.sjy.akita.R;
import com.sjy.akita.databinding.DelegateMainBinding;
import com.sjy.akita.fragments.list.MultiRecycleviewDelegate;
import com.sjy.akita.fragments.list.RecycleviewDelegate;
import com.sjy.akita.fragments.pop.PopDelegate;
import com.sjy.akita.fragments.webview.WebViewDelegate;
import com.sjy.akita.fragments.webview.X5WebViewActivity;
import com.sjy.akita_core.delegate.AkitaDelegate;
import com.sjy.akita_ui.alertpicker.AlertPicker;
import com.sjy.akita_ui.alertpicker.PickerType;
import com.sjy.akita_ui.alertpicker.callback.IOnSelect;
import com.sjy.akita_ui.toaster.AkitaToast;

import java.util.Calendar;

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

        v.tvBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(new PopDelegate());
            }
        });

        v.tvBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(_mActivity,X5WebViewActivity.class));
            }
        });
        v.tvBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(new WebViewDelegate());
            }
        });


        /*Intent intent = new Intent();
        intent.setAction("hehe");
        pendingIntent = PendingIntent.getBroadcast(_mActivity, 0, intent, 0);
        alarmManager= (AlarmManager) _mActivity.getSystemService(Context.ALARM_SERVICE);
        int offset= calculateTime();
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
        AkitaToast.showCustomToast(LayoutInflater.from(_mActivity).inflate(R.layout.toast_view,null));
    }

    private int calculateTime(){
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int lefthour;
        if(hour>=20){
            lefthour= 24-(hour-17);
        }else if(hour>17&&hour<20) {
            lefthour= 0;
        }else {
            lefthour= 17-hour;
        }
        return lefthour*60*60*1000;
    }

}
