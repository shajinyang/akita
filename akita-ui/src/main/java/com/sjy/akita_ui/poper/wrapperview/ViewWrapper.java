package com.sjy.akita_ui.poper.wrapperview;

import android.view.View;

/**
 * Created by sjy on 2017/12/2.
 */

public class ViewWrapper {
    private int Height;
    private int Width;
    private View view;

    public int getHeight() {
        return view.getLayoutParams().height;
    }

    public int getWidth(){return view.getLayoutParams().width;}

    public void setHeight(int height) {
        view.getLayoutParams().height = height;
        view.requestLayout();
    }

    public void setWidth(int width){
        view.getLayoutParams().width=width;
        view.requestLayout();
    }


    public ViewWrapper(View view) {
        this.view = view;
    }


}
