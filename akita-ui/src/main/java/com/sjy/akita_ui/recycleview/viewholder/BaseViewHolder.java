package com.sjy.akita_ui.recycleview.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by sjy on 2017/6/7.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(Context context, View itemView, ViewGroup parent)
    {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }


    public static BaseViewHolder get(Context context, ViewGroup parent, int layoutId)
    {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        BaseViewHolder holder = new BaseViewHolder(context, itemView, parent);
        return holder;
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int viewId, String text)
    {
        if(text==null||text.equals("null")){
            text="";
        }
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }



    public BaseViewHolder setText(int viewId, SpannableString text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseViewHolder setTextColor(int viewId,int color){
        TextView tv = getView(viewId);
        tv.setTextColor(color);
        return this;
    }

    public BaseViewHolder setBackground(int viewId, int id)
    {
        View view = getView(viewId);
        view.setBackground(mContext.getResources().getDrawable(id));
        return this;
    }

    /**
     * 设置点击事件
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        View view = getView(viewId);
        if(view==null)return this;
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置可见状态
     * @param viewId
     * @param visibility
     * @return
     */
    public BaseViewHolder setVisibility(int viewId,int visibility){
        View view = getView(viewId);
        view.setVisibility(visibility);
        return  this;
    }


    /**
     * 设置控件选择状态
     * @param viewId
     * @param isselect
     * @return
     */
    public BaseViewHolder setSelected(int viewId,boolean isselect){
        View view = getView(viewId);
        view.setSelected(isselect);
        return  this;
    }

    /**
     * 改变控件选择状态
     * @param viewId
     * @return
     */
    public BaseViewHolder changeSelected(int viewId){
        View view = getView(viewId);
        if(view.isSelected()){
            view.setSelected(false);
        }else {
            view.setSelected(true);
        }

        return  this;
    }


}
