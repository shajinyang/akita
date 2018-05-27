package com.sjy.akita_ui.alertpicker.util;

import android.content.Context;

import com.google.gson.Gson;
import com.sjy.akita_ui.alertpicker.bean.JsonBean;


import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by sjy on 2018/5/27.
 */

public class ParseJson {


    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private ParseJson(Context context){
        String res= getJson(context);
        parseData(res);
        getCityandDisStrict();

    }

    public static ParseJson create(Context context){
        return new ParseJson(context);
    }

    public ArrayList<JsonBean> getProvince(){
        return options1Items;
    }

    public ArrayList<ArrayList<String>> getCity(){
        return  options2Items;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getDistrict(){
        return  options3Items;
    }

    private String getJson(Context context){
        String JsonData = new GetJsonDataUtil().getJson(context, "province.json");//获取assets目录下的json文件数据
        return JsonData;
    }

    private void parseData(String result) {//json 解析
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                options1Items.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCityandDisStrict(){
        for (JsonBean jsonBean:options1Items
             ) {
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            if(jsonBean.getCityList()!=null) {
                for (int c = 0; c < jsonBean.getCityList().size(); c++) {//遍历该省份的所有城市
                    String CityName = jsonBean.getCityList().get(c).getName();
                    CityList.add(CityName);//添加城市
                    ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                    //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                    if (jsonBean.getCityList().get(c).getArea() == null
                            || jsonBean.getCityList().get(c).getArea().size() == 0) {
                        City_AreaList.add("");
                    } else {
                        City_AreaList.addAll(jsonBean.getCityList().get(c).getArea());
                    }
                    Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                }
                /**
                 * 添加城市数据
                 */
                options2Items.add(CityList);

                /**
                 * 添加地区数据
                 */
                options3Items.add(Province_AreaList);
            }
        }
    }
}
