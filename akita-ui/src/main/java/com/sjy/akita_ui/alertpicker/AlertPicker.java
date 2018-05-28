package com.sjy.akita_ui.alertpicker;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.sjy.akita_ui.alertpicker.bean.JsonBean;
import com.sjy.akita_ui.alertpicker.callback.IOnSelect;
import com.sjy.akita_ui.alertpicker.util.ParseJson;

import java.util.ArrayList;

/**
 * 城市弹框选择器
 * Created by sjy on 2018/5/27.
 */

public class AlertPicker {
    private PickerType TYPE=PickerType.PROVINCE_CITY_DISTRICT;
    private OptionsPickerView pvOptions;
    private IOnSelect iOnSelect;
    private Context context;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();


    private AlertPicker(Context context){
        this.context=context;
        pvOptions=new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if(iOnSelect!=null){
                    iOnSelect.onSelect(
                            options1Items.get(options1).getPickerViewText(),
                            options2Items.get(options1).get(options2),
                            options3Items.get(options1).get(options2).get(options3)
                            );
                }
            }
        }).build();
    }

    public static AlertPicker create(Context context){
        return new AlertPicker(context);
    }

    public AlertPicker type(PickerType TYPE){
        this.TYPE=TYPE;
        return this;
    }

    public AlertPicker onSelect(IOnSelect iOnSelect){
        this.iOnSelect=iOnSelect;
        return this;
    }


    public void show(){
        if(TYPE==PickerType.PROVINCE_CITY_DISTRICT){
            ParseJson parseJson= ParseJson.create(context);
            options1Items=parseJson.getProvince();
            options2Items=parseJson.getCity();
            options3Items=parseJson.getDistrict();
            pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
            pvOptions.show();
        }else if(TYPE==PickerType.PROVINCE_CITY){
            ParseJson parseJson= ParseJson.create(context);
            options1Items=parseJson.getProvince();
            options2Items=parseJson.getCity();
            pvOptions.setPicker(options1Items, options2Items);//三级选择器
            pvOptions.show();
        }
    }
}
