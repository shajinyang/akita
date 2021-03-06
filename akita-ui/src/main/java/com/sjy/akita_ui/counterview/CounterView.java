package com.sjy.akita_ui.counterview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 倒计时viw
 * Created by sjy on 2017/9/21.
 */

public class CounterView extends AppCompatTextView {
    private OnTimeListener onTimeListener;
    private boolean isCount=false;
    private String overtxt="重新获取";
    private String begaintxt="获取验证码";
    private long totalTime=60000;//60秒

    public CounterView(Context context) {
        this(context, null);
    }

    public CounterView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setText(begaintxt);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onTimeListener==null)return;
                if(onTimeListener.onClickTime()) {
                    if (isCount == false) {
                        begainTime();
                    }
                }
            }
        });
    }


    //设置开始文字显示
    public CounterView setNromalTxt(String text){
        begaintxt=text;
        setText(text);
        return this;
    }
    //设置结束文字显示
    public CounterView setTimeOverTxt(String text){
        overtxt=text;
        return this;
    }
    //设置开始结束监听
    public CounterView setOnTimeListener(OnTimeListener onTimeListener){
        this.onTimeListener=onTimeListener;
        return this;
    }
    //设置倒计时时间
    public CounterView setCountTime(long time){
        totalTime=time;
        return this;
    }


    private void begainTime(){
        int begain= (int) (totalTime/1000);
        ValueAnimator valueAnimator= ValueAnimator.ofInt(begain,0);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                String av= animation.getAnimatedValue().toString()+"";
                setMyText(av);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isCount=true;
                setEnabled(false);
                if(onTimeListener!=null) {
                    onTimeListener.startTime();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isCount=false;
                setText(overtxt);
                setEnabled(true);
                if(onTimeListener!=null) {
                    onTimeListener.endTime();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.setDuration(totalTime);
        valueAnimator.start();
    }

    private void setMyText(String righttime){
        setText(righttime+"s");
    }


}
