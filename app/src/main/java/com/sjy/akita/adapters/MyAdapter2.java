package com.sjy.akita.adapters;

import android.content.Context;

import com.sjy.akita.R;
import com.sjy.akita_ui.recycleview.AkitaMultiRecycleViewAdapter;
import com.sjy.akita_ui.recycleview.interfaces.MultiItemTypeSupport;
import com.sjy.akita_ui.recycleview.viewholder.BaseViewHolder;

/**
 * Created by sjy on 2018/5/28.
 */

public class MyAdapter2 extends AkitaMultiRecycleViewAdapter<String>{
    public MyAdapter2(Context context, MultiItemTypeSupport<String> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    @Override
    public void convert(BaseViewHolder holder, String s, int position) {
        holder.setText(R.id.tv,s);
    }
}
