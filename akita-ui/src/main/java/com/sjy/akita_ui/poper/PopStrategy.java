package com.sjy.akita_ui.poper;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 弹框样式策略
 * Created by sjy on 2018/5/29.
 */

public abstract class PopStrategy {
     protected final static int POP_BG_COLOR=0x50000000;
     protected RelativeLayout parentView;
     protected View anchorView;
     protected View popView;

     protected boolean isOpen=false;
     protected boolean isAnim=false;

    public PopStrategy( View anchorView, View popView) {
        this.anchorView = anchorView;
        this.popView = popView;
        initChildView();
    }

    public RelativeLayout getParentView() {
        return parentView;
    }



    /**
     * 弹框位置计算
     */
    protected abstract void calculateParams(RelativeLayout.LayoutParams params);

    /**
     * 初始化子view
     */
    protected abstract void initChildView();

    /**
     * 添加view
     */
    protected abstract void addViewToContent();

    /**
     * 弹框打开动画
     */
    protected abstract void showAnim();

    /**
     * 弹框关闭动画
     */
    protected abstract void closeAnim();

    /**
     * 弹框关闭
     */
    protected abstract void dimiss();





}
