package com.sjy.akita.adapters;

import android.content.Context;

import com.sjy.akita.R;
import com.sjy.akita_ui.recycleview.AkitaRecycleViewAdapter;
import com.sjy.akita_ui.recycleview.viewholder.BaseViewHolder;

/**
 * Created by sjy on 2018/5/28.
 */

public class MyAdapter extends AkitaRecycleViewAdapter<String> {

    public MyAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(BaseViewHolder holder, String s, int position) {
        holder.setText(R.id.tv,s);
    }
}
