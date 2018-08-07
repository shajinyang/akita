package com.sjy.akita_core.net.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by sjy on 2018/8/7.
 */

public class ReflectUtil {
    /**
     * 获取对象的属性-值  map集合
     * @param o
     * @return
     */
    public static WeakHashMap<String,Object> getFiledsInfo(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        WeakHashMap infoMap=new WeakHashMap();
        for(int i=0;i<fields.length;i++){
            infoMap.put(fields[i].getName(), getFieldValueByName(fields[i].getName(),o));
        }
        return infoMap;
    }

    /**
     * 根据属性名获取属性值
     * */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
