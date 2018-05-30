package com.sjy.akita_ui.poper;

import android.app.Activity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.sjy.akita_common.utils.ScreenUtils;
import com.sjy.akita_ui.R;
import com.sjy.akita_ui.poper.popAnim.AnimationUtil;
import com.sjy.akita_ui.poper.strategy.BottomPopStrategy;
import com.sjy.akita_ui.poper.strategy.LeftPopStrategy;
import com.sjy.akita_ui.poper.strategy.RightPopStrategy;
import com.sjy.akita_ui.poper.strategy.TopPopStrategy;

/**
 * 弹框
 * Created by sjy on 2018/5/29.
 */

public class Poper {

    private PopStrategy popStrategy;
    private final PopOrientation popOrientation;
    private final View anchorView;
    private final boolean isTouchOutCancelable;
    private final View popView;


    public Poper(PopOrientation popOrientation, View anchorView, boolean isTouchOutCancelable,View popView) {
        this.popOrientation = popOrientation;
        this.anchorView = anchorView;
        this.isTouchOutCancelable = isTouchOutCancelable;
        this.popView=popView;
        initStrategy();
    }



    public static PoperBuilder builder(){
        return new PoperBuilder();
    }


    public void show(){
        popStrategy.showAnim();
    }


    public void dismiss(){
        popStrategy.dimiss();
    }

    /**
     * 初始化弹框策略
     */
    private void initStrategy(){
        if(popOrientation==PopOrientation.FROM_TOP){
            popStrategy=new TopPopStrategy(anchorView,popView);
        }else if(popOrientation==PopOrientation.FROM_LEFT){
            popStrategy=new LeftPopStrategy(anchorView,popView);
        }else if(popOrientation==PopOrientation.FROM_RIGHT){
            popStrategy=new RightPopStrategy(anchorView,popView);
        }else if(popOrientation==PopOrientation.FROM_BOTTOM){
            popStrategy=new BottomPopStrategy(anchorView,popView);
        }
        setKeyBackCancelable(true);
    }

    /**
     * 监听按返回键
     * @param isCancelable
     */
    private void setKeyBackCancelable(boolean isCancelable) {
        popView.setFocusable(isCancelable);
        popView.setFocusableInTouchMode(isCancelable);
        if (isCancelable) {
            popView.setOnKeyListener(onKeyBackListener);
        } else {
            popView.setOnKeyListener(null);
        }
    }


    private View.OnKeyListener onKeyBackListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_DOWN ) {
                dismiss();
                return true;
            }
            return false;
        }
    };

}
