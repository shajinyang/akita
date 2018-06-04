package com.sjy.akita_ui.poper.strategy;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.sjy.akita_common.utils.ScreenUtils;
import com.sjy.akita_ui.R;
import com.sjy.akita_ui.poper.PopOrientation;
import com.sjy.akita_ui.poper.PopStrategy;
import com.sjy.akita_ui.poper.popAnim.AnimationUtil;

/**
 * Created by sjy on 2018/5/29.
 */

public class RightPopStrategy extends PopStrategy {

    public RightPopStrategy(  View anchorView, View popView) {

        super( anchorView, popView);
    }

    @Override
    protected void calculateParams(FrameLayout.LayoutParams params) {
        if(anchorView==null){
            return;
        }
        //相对屏幕的绝对坐标
        int[] position=new int[2];
        int screenW= ScreenUtils.getScreenWidth(anchorView.getContext());
        anchorView.getLocationInWindow(position);

        int popMarginright=screenW-position[0];

        params.setMargins(0,0,popMarginright,0);
    }

    @Override
    protected void initChildView( ) {
        parentView=new RelativeLayout(popView.getContext());
        parentView.setId(R.id.akita_ui_pop_bg);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        parentView.setLayoutParams(params);
        parentView.setBackgroundColor(POP_BG_COLOR);
        parentView.setGravity(Gravity.RIGHT);
        parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeAnim();
            }
        });
        parentView.addView(popView,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        parentView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void addViewToContent() {
        FrameLayout.LayoutParams childparams=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        calculateParams(childparams);
        ((Activity) popView.getContext()).addContentView(parentView,childparams);
    }

    @Override
    protected void showAnim() {
        if(isAnim||isOpen)return;
        addViewToContent();
        AnimationUtil.slideInFromRight(popView.getContext(),popView);
        popView.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                popView.setVisibility(View.VISIBLE);
                parentView.setVisibility(View.VISIBLE);
                isAnim=true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnim=false;
                isOpen=true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        AnimationUtil.alphaIn(popView.getContext(),parentView);
    }

    @Override
    protected void closeAnim() {
        if(!isOpen||isAnim)return;
        AnimationUtil.slideOutToRight(popView.getContext(),popView);
        popView.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnim=true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                parentView.setVisibility(View.GONE);
                ((ViewGroup)parentView.getParent()).removeView(parentView);//移除控件
                isAnim=false;
                isOpen=false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void dimiss() {
        closeAnim();
    }

}
