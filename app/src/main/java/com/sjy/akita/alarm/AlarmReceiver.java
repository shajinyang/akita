package com.sjy.akita.alarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.sjy.akita.R;
import com.sjy.akita_core.log.AkitaLog;

import java.util.Calendar;

/**
 * Created by sjy on 2018/5/28.
 */

public class AlarmReceiver extends BroadcastReceiver {

    static final String ACTION_ALARM="hehe";
    static final String ACTION_ALARM_ONCE="once";
    static final String ACTION_CANCEL="cancel";
    static final String ACTION_DELAY="delay";
    static final String ACTION_NEVER="never";

    NotificationManager notificationManager=null;
    Notification notification = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE); // 获取系统提供的通知管理服务
        if(intent!=null){
            if(ACTION_ALARM.equals(intent.getAction())){
                setAlarm(context);
                notifySecondNotification(context);
                AkitaLog.e("闹钟启动");
            }else if(ACTION_CANCEL.equals(intent.getAction())){

                notificationManager.cancel(2);
                AkitaLog.e("点击了取消");

            }else if(ACTION_DELAY.equals(intent.getAction())){
                setOnceAlarm(context);
                notificationManager.cancel(2);
                AkitaLog.e("点击了稍后提醒");

            }else if(ACTION_NEVER.equals(intent.getAction())){

                notificationManager.cancel(2);
                AkitaLog.e("点击了不再提示");

            }else if(ACTION_ALARM_ONCE.equals(intent.getAction())){
                notifySecondNotificationOnce(context);
                AkitaLog.e("提前提醒");
            }
        }

    }

    private void notifySecondNotification(Context context)
    {
        Intent canleIntent = new Intent(ACTION_CANCEL);
        Intent delayIntent = new Intent(ACTION_DELAY);
        Intent neverIntent = new Intent(ACTION_NEVER);
        PendingIntent canelPendingIntent = PendingIntent.getBroadcast(context, 0, canleIntent, 0);
        PendingIntent delayPendingIntent = PendingIntent.getBroadcast(context, 0, delayIntent, 0);
        PendingIntent neverPendingIntent = PendingIntent.getBroadcast(context, 0, neverIntent, 0);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.noyification_view);
        remoteViews.setOnClickPendingIntent(R.id.delay_btn, delayPendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.cancel_btn,canelPendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.never_btn,neverPendingIntent);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            notification = new Notification.Builder(context)
                    .setContentTitle("直销家园") // 创建通知的标题
                    .setContentText("今晚有已核实的顾客需求更新，记得晚上8点查看抢领噢！")
                    .setSmallIcon(R.mipmap.timg)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                            R.mipmap.timg))// 创建通知的内容
                    .setDefaults(Notification.DEFAULT_ALL)
                    // 设置通知提醒方式为系统默认的提醒方式
                    .setCustomBigContentView(remoteViews)
                    .setAutoCancel(true)
                    .build();
            notificationManager.notify(2, notification); // 发送通知
        }
    }

    private void setAlarm(Context _mActivity){
        Intent intent = new Intent();
        intent.setAction(ACTION_ALARM_ONCE);
        PendingIntent  pendingIntent = PendingIntent.getBroadcast(_mActivity, 0, intent, 0);
        AlarmManager alarmManager= (AlarmManager) _mActivity.getSystemService(Context.ALARM_SERVICE);
        int offset= calculateTime();
        long triggerAtTime = SystemClock.elapsedRealtime() + offset;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            alarmManager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);

        } else {

            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime, pendingIntent);

        }
    }

    private void setOnceAlarm(Context _mActivity){
        int lef=calculateTimeOnce();
        if(lef<0)return;
        Intent intent = new Intent();
        intent.setAction(ACTION_ALARM_ONCE);
        PendingIntent  pendingIntent = PendingIntent.getBroadcast(_mActivity, 0, intent, 0);
        AlarmManager alarmManager= (AlarmManager) _mActivity.getSystemService(Context.ALARM_SERVICE);
        int offset= lef;
        long triggerAtTime = SystemClock.elapsedRealtime() + offset;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            alarmManager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);

        } else {

            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime, pendingIntent);

        }
    }

    private void notifySecondNotificationOnce(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            notification = new Notification.Builder(context)
                    .setContentTitle("直销家园") // 创建通知的标题
                    .setContentText("^_^ 晚上8点就可以抢领顾客需求了，提前做好准备噢!")
                    .setSmallIcon(R.mipmap.timg)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                            R.mipmap.timg))// 创建通知的内容
                    .setDefaults(Notification.DEFAULT_ALL)
                    // 设置通知提醒方式为系统默认的提醒方式
                    .setAutoCancel(true)
                    .build();
            notificationManager.notify(2, notification); // 发送通知
        }
    }

    private int calculateTime(){
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int lefthour;
        if(hour>=20){
            lefthour= 24-(hour-17);
        }else if(hour>17&&hour<20) {
            lefthour= 24;
        }else {
            lefthour= 24+17-hour;
        }
        return lefthour*60*60*1000;
    }

    private int calculateTimeOnce(){
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int lefthour;
        if(hour>=20){
            lefthour= -1;
        }else if(hour>19&&hour<20) {
            lefthour= 0;
        }else {
            lefthour= 19-hour;
        }
        return lefthour*60*60*1000;
    }




}
