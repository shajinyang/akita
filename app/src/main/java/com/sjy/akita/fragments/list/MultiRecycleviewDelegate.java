package com.sjy.akita.fragments.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.sjy.akita.R;
import com.sjy.akita.adapters.MyAdapter;
import com.sjy.akita.adapters.MyAdapter2;
import com.sjy.akita.databinding.DelegateRecycleviewBinding;
import com.sjy.akita_core.delegate.AkitaDelegate;
import com.sjy.akita_ui.recycleview.callback.ICheckFullPage;
import com.sjy.akita_ui.recycleview.callback.IOnFootClickListener;
import com.sjy.akita_ui.recycleview.callback.IOnLoadMoreListener;
import com.sjy.akita_ui.recycleview.interfaces.MultiItemTypeSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjy on 2018/5/28.
 */

public class MultiRecycleviewDelegate extends AkitaDelegate<DelegateRecycleviewBinding> {
    private List<String> list=new ArrayList<>();
    private MyAdapter2 adapter;

    int  count=0;
    @Override
    protected Object setLayout() {
        return R.layout.delegate_recycleview;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initView();
        bindData();

    }

    private void initView(){
        v.recycleview.setLayoutManager(new GridLayoutManager(_mActivity,3));
        adapter= new MyAdapter2(_mActivity, new MultiItemTypeSupport<String>() {
            @Override
            public int getLayoutId(int itemType) {
                if(itemType==0){
                    return R.layout.recycleview_item;
                }
                return R.layout.recycleview_item2;
            }

            @Override
            public int getItemViewType(int position, String s) {
                if(position%2==0){
                    return 0;
                }else {
                    return 1;
                }

            }
        });
        adapter.bindToRecycleview(v.recycleview);
    }

    private void bindData(){
        for (int i=0;i<60;i++){
            list.add("第"+i+"项");
        }
        adapter.setList(list);
        adapter.setIOnFootClickListener(new IOnFootClickListener() {
            @Override
            public void onClickFoot() {
                Toast.makeText(_mActivity,"点击底部",Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setIOnLoadMoreListener(new IOnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                v.recycleview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                        if(count==2){
                            adapter.showLoadEmpty();
                        }else {
                            adapter.setList(list);
                        }

                    }
                },1000);
            }
        });
    }

    private void loadData(){
        count++;
        for (int i=0;i<10;i++){
            list.add("新数据"+i);
        }
    }
}
