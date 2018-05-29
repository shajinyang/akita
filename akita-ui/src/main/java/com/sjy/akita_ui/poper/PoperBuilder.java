package com.sjy.akita_ui.poper;

import android.view.View;

/**
 * Created by sjy on 2018/5/29.
 */

public class PoperBuilder {
    boolean isInit=false;

    private  PopOrientation popOrientation;
    private  View anchorView;
    private  boolean isTouchOutCancelable;
    private View popView;

    public PoperBuilder setOrientation(PopOrientation popOrientation){
        this.popOrientation=popOrientation;
        return this;
    }

    public PoperBuilder setAnchorView(View anchorView){
        this.anchorView=anchorView;
        return this;
    }

    public PoperBuilder setPopView(View popView){
        this.popView=popView;
        return this;
    }

    public PoperBuilder setIsTouchOutCancelable(boolean isTouchOutCancelable){
        this.isTouchOutCancelable=isTouchOutCancelable;
        return this;
    }

    public Poper build(){
        return new Poper(popOrientation, anchorView, isTouchOutCancelable,popView);
    }
}
