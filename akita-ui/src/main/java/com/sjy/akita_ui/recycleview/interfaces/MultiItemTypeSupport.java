package com.sjy.akita_ui.recycleview.interfaces;

public interface MultiItemTypeSupport<T>
{
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}