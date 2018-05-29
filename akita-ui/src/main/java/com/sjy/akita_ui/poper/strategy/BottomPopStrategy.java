package com.sjy.akita_ui.poper.strategy;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.sjy.akita_common.utils.ScreenUtils;
import com.sjy.akita_ui.R;
import com.sjy.akita_ui.poper.PopOrientation;
import com.sjy.akita_ui.poper.PopStrategy;
import com.sjy.akita_ui.poper.popAnim.AnimationUtil;

/**
 * Created by sjy on 2018/5/29.
 */

public class BottomPopStrategy extends PopStrategy {


    public BottomPopStrategy( View anchorView, View popView) {
        super( anchorView, popView);
    }

    @Override
    protected void calculateParams(RelativeLayout.LayoutParams params) {
        if(anchorView==null){
            return;
        }
        //相对屏幕的绝对坐标
        int[] position=new int[2];
        int screenH= ScreenUtils.getScreenHeight(anchorView.getContext());
        anchorView.getLocationInWindow(position);

        int popMarginbottom=screenH-position[1];
        params.setMargins(0,0,0,popMarginbottom);
    }

    @Override
    protected void showAnim() {
        initChildView();
        AnimationUtil.slideInFromBottom(popView.getContext(),popView);
        popView.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                popView.setVisibility(View.VISIBLE);
                parentView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        AnimationUtil.alphaIn(popView.getContext(),parentView);
    }

    @Override
    protected void closeAnim() {
        AnimationUtil.slideOutToBottom(popView.getContext(),popView);
        popView.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                parentView.setVisibility(View.GONE);
                ((ViewGroup)parentView.getParent()).removeView(parentView);//移除控件
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

    @Override
    protected void initChildView() {
        parentView=new RelativeLayout(popView.getContext());
        parentView.setId(R.id.akita_ui_pop_bg);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        parentView.setLayoutParams(params);
        parentView.setBackgroundColor(POP_BG_COLOR);
        parentView.setGravity(Gravity.BOTTOM);
        parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeAnim();
            }
        });
        parentView.addView(popView,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams childparams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        calculateParams(childparams);
        parentView.setVisibility(View.INVISIBLE);
        ((Activity) popView.getContext()).addContentView(parentView,childparams);
    }
}
