package com.sjy.akita_common.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间处理类
 * Created by sjy on 2018/5/25.
 */

public final class DateUtil {

    /**
     *
     * @Title: timestamp
     * @Description:
     * @return 转化为秒
     * long
     * @throws
     */
    public static long timestamp(){
        return System.currentTimeMillis()/1000;
    }

    /**
     *
     * @Title: timestamp
     * @Description:
     * long 毫秒
     * @throws
     */
    public static long timestamp2(){
        return System.currentTimeMillis();
    }


    /**
     * 毫秒数转换成yyyy-MM-dd
     */
    public static String getTime(long timeMillis){
        Date d = new Date(timeMillis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    /**
     * 毫秒数转换成yyyy-MM-dd HH:mm
     */
    public static String getTime2(long timeMillis){
        Date d = new Date(timeMillis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(d);
    }


    /**
     * 时间转化为  年  月  日 数组
     * @param  mss 单位秒
     * @return
     */
    public static String[] dateToArray(long mss){
        String[] args;
        String date=getTime(mss*1000);
        args= date.split("-");
        if(args.length==3){
            args[0]=args[0].length()==1?"0"+args[0]:args[0];
            args[1]=args[1].length()==1?"0"+args[1]:args[1];
            args[2]=args[2].length()==1?"0"+args[2]:args[2];
        }
        return args;
    }




}
