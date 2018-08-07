package com.sjy.akita.fragments.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.sjy.akita.R;
import com.sjy.akita.adapters.MyAdapter;
import com.sjy.akita.databinding.DelegateRecycleviewBinding;
import com.sjy.akita_core.delegate.AkitaDelegate;
import com.sjy.akita_ui.recycleview.callback.ICheckFullPage;
import com.sjy.akita_ui.recycleview.callback.IOnFootClickListener;
import com.sjy.akita_ui.recycleview.callback.IOnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjy on 2018/5/28.
 */

public class RecycleviewDelegate extends AkitaDelegate<DelegateRecycleviewBinding> {
    private List<String> list=new ArrayList<>();
    private MyAdapter adapter;
    @Override
    protected Object setLayout() {
        return R.layout.delegate_recycleview;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initView();
        bindRecycleviewData();


    }


    private void initView(){
        adapter=new MyAdapter(_mActivity,R.layout.recycleview_item);
        v.recycleview.setLayoutManager(new GridLayoutManager(_mActivity,2));
        adapter.bindToRecycleview(v.recycleview);
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
                        adapter.setList(list);
                    }
                },1000);
            }
        });
    }


    private void bindRecycleviewData(){
        v.recycleview.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<0;i++){
                    list.add("第"+i+"项");
                }
                adapter.setList(list);
            }
        },1000);


    }

    //加载更多数据
    private void loadData(){
        for (int i=0;i<10;i++){
            list.add("新数据"+i);
        }
    }

    //重新加载数据
    private void loadNewData(){
        list=new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add("重新加载的新数据"+i);
        }
    }
}
