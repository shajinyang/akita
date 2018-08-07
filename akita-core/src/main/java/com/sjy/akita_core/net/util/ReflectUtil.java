package com.sjy.akita_core.net.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
        ArrayList<Field> fields=getFieldByClasss(o);
        WeakHashMap infoMap=new WeakHashMap();
        for(int i=0;i<fields.size();i++){
            //去除null和序列化id
            if(null!=getFieldValueByFieldName(fields.get(i).getName(),o)&&!"serialVersionUID".equals(fields.get(i).getName())) {
                infoMap.put(fields.get(i).getName(), getFieldValueByFieldName(fields.get(i).getName(), o));
            }
        }
        return infoMap;
    }

    /**
     * 获取对象的属性-值  map集合
     * @param o
     * @return
     */
    public static HashMap<String,Object> getFiledsInfoHash(Object o){
        ArrayList<Field> fields=getFieldByClasss(o);
        HashMap infoMap=new HashMap();
        for(int i=0;i<fields.size();i++){
            //去除null和序列化id
            if(null!=getFieldValueByFieldName(fields.get(i).getName(),o)&&!"serialVersionUID".equals(fields.get(i).getName())) {
                infoMap.put(fields.get(i).getName(), getFieldValueByFieldName(fields.get(i).getName(), o));
            }
        }
        return infoMap;
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    private static Object getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getField(fieldName);
            return field.get(object);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据属性名获取属性元素，包括各种安全范围和所有父类
     *
     * @param object
     * @return
     */
    private static ArrayList<Field> getFieldByClasss(Object object) {
        ArrayList<Field> fieldList = new ArrayList<>();
        Class<?> clazz = object.getClass();

        for (;clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields= clazz.getDeclaredFields();
                for (Field fi:fields
                        ) {
                    fieldList.add(fi);
                }
            } catch (Exception e) {

            }
        }
        return fieldList;
    }
}
