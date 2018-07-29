package com.sjy.akita_ui.recycleview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sjy.akita_ui.R;
import com.sjy.akita_ui.recycleview.callback.ICheckFullPage;
import com.sjy.akita_ui.recycleview.callback.IOnFootClickListener;
import com.sjy.akita_ui.recycleview.callback.IOnLoadMoreListener;
import com.sjy.akita_ui.recycleview.interfaces.MultiItemTypeSupport;
import com.sjy.akita_ui.recycleview.viewholder.BaseViewHolder;

import java.util.List;

/**
 * 复合多布局适配器带下拉加载更多
 *
 * @param <T>
 */
public abstract class AkitaMultiRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    Context mContext;
    IOnFootClickListener iOnFootClickListener;
    IOnLoadMoreListener iOnLoadMoreListener;
    int mLoadMoreId = R.layout.akita_recycleview_item_footer_more;//加载更多布局
    int mLoadErrorId = R.layout.akita_recycleview_item_footer_error;//加载错误布局
    int mLoadEmptyId = R.layout.akita_recycleview_item_footer_empty;//加载空数据布局
    int mContentEmptyId=R.layout.akita_recycleview_item_content_empty;//内容空数据布局
    protected List<T> data;
    LayoutInflater mInflater;
    private final static int CONTENT_EMPTY_VIEW=104;//内容空数据布局
    private final static int CONTENT_VIEW = 100;//内容布局
    private final static int LOAD_MORE_VIEW = 101;//加载更多
    private final static int LOAD_MORE_EMPTY = 102;//没有更多数据了
    private final static int LOAD_ERROR = 103;//加载数据失败
    //记载样式（CONTENT_VIEW，普通加载；LOAD_MORE_VIEW,加载更多；LOAD_MORE_EMPTY，没有更多数据；LOAD_ERROR，加载数据失败）
    private int loadType = CONTENT_VIEW;
    private RecyclerView recyclerView;
    private boolean tagFullScreen =false;//数据是否能够填满一屏

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public AkitaMultiRecycleViewAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    /**
     * 绑定recycleview
     *
     * @param recyclerView
     */
    public void bindToRecycleview(RecyclerView recyclerView) {
        if (getRecyclerView() == null) {
            setRecyclerView(recyclerView);
        }
        setLookUpSize();
        recyclerView.setAdapter(this);

    }

    /**
     * 设置数据源
     *
     * @param mData
     */
    public void setList(List<T> mData) {
        tagFullScreen=false;
        this.data =mData;
        //data为null 或者空时为空布局视图
        if(mData==null||mData.size()==0){
            loadType=CONTENT_EMPTY_VIEW;
            notifyDataSetChanged();
        }
        //data不为null 默认为加载视图
        else {
            showLoadMore();//先显示加载视图并刷新数据
            //判断刷新后的数据是否满一屏，再决定是否显示其他foot
            isFullPage(recyclerView, new ICheckFullPage() {
                @Override
                public void checkFullPage(boolean result) {
                    tagFullScreen=result;
                    //不满一屏，切换为无更多数据布局
                    if(!result){
                        showLoadEmpty();
                    }
                    //否则为加载布局
                    else {
                        showLoadMore();
                    }
                }
            });

        }
    }

    /**
     * 设置自定义加载view
     *
     * @param viewId
     */
    public void setLoadMoreView(int viewId) {
        mLoadMoreId = viewId;
    }

    /**
     * 设置自定义加载错误view
     *
     * @param viewId
     */
    public void setLoadErrorView(int viewId) {
        mLoadErrorId = viewId;
    }

    /**
     * 设置自定义加载空数据view
     *
     * @param viewId
     */
    public void setLoadEmptyView(int viewId) {
        mLoadEmptyId = viewId;
    }
    /**
     * 设置空内容布局view
     * @param viewId
     */
    public void setContentEmptyView(int viewId){
        mContentEmptyId=viewId;
    }

    /**
     * 设置底部点击回调
     */
    public void setIOnFootClickListener(IOnFootClickListener iOnFootClickListener) {
        this.iOnFootClickListener = iOnFootClickListener;
    }

    /**
     * 设置滑动底部自动加载回调
     */
    public void setIOnLoadMoreListener(IOnLoadMoreListener iOnLoadMoreListener) {
        this.iOnLoadMoreListener = iOnLoadMoreListener;
    }


    /**
     * 显示加载更多
     */
    public void showLoadMore(){
        loadType=LOAD_MORE_VIEW;
        if(data!=null&&data.size()>0){
            notifyDataSetChanged();
        }
    }

    /**
     * 显示加载错误
     */
    public void showLoadError(){
        loadType=LOAD_ERROR;
        if(data!=null){
            notifyDataSetChanged();
        }
    }

    /**
     * 显示没有更多数据
     */
    public void showLoadEmpty(){
        loadType=LOAD_MORE_EMPTY;
        if(data!=null){
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        autoLoadMore(position);
        if (holder.getItemViewType() == LOAD_MORE_VIEW) {

        } else if (holder.getItemViewType() == LOAD_ERROR) {
            holder.setOnClickListener(R.id.akita_ui_recycleview_footer, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iOnFootClickListener != null) {
                        iOnFootClickListener.onClickFoot();
                    }
                }
            });
        } else if (holder.getItemViewType() == LOAD_MORE_EMPTY) {

        }else if (holder.getItemViewType() == CONTENT_EMPTY_VIEW) {

        }else {
            convert(holder, data.get(position), position);
        }

    }

    @Override
    public int getItemCount() {
        if (loadType == LOAD_MORE_VIEW) {
            return data.size() + 1;
        } else if (loadType == LOAD_ERROR) {
            return data.size() + 1;
        } else if (loadType == LOAD_MORE_EMPTY) {
            return data.size() + 1;
        }
        //list为空数据时，直接返回空布局个数
        else if(loadType==CONTENT_EMPTY_VIEW){
            return 1;
        }else {
            return data.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (loadType == LOAD_MORE_VIEW) {
            if (position < data.size()) {
                return mMultiItemTypeSupport.getItemViewType(position, data.get(position));
            } else {
                return LOAD_MORE_VIEW;
            }
        } else if (loadType == LOAD_ERROR) {
            if (position < data.size()) {
                return mMultiItemTypeSupport.getItemViewType(position, data.get(position));
            } else {
                return LOAD_ERROR;
            }
        } else if (loadType == LOAD_MORE_EMPTY) {
            if (position < data.size()) {
                return mMultiItemTypeSupport.getItemViewType(position, data.get(position));
            } else {
                return LOAD_MORE_EMPTY;
            }
        }
        else if(loadType==CONTENT_EMPTY_VIEW){
            return CONTENT_EMPTY_VIEW;
        }
        else {
            return mMultiItemTypeSupport.getItemViewType(position, data.get(position));
        }


    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == LOAD_MORE_VIEW) {
            BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, mLoadMoreId);
            return viewHolder;
        } else if (viewType == LOAD_ERROR) {
            BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, mLoadErrorId);
            return viewHolder;
        } else if (viewType == LOAD_MORE_EMPTY) {
            BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, mLoadEmptyId);
            return viewHolder;
        } else if(viewType==CONTENT_EMPTY_VIEW){
            BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, mContentEmptyId);
            return viewHolder;
        }else {
            int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
            BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, layoutId);
            return viewHolder;
        }
    }

    public abstract void convert(BaseViewHolder holder, T t, int position);

    /**
     * 根据位置判断是否需要自动加载
     *
     * @param position
     */
    private void autoLoadMore(int position) {
        if (data == null || data.size() == 0) return;
        if (position < data.size() - 1) {
            return;
        }
        //自动加载更多条件（1，滑动最后。2，当前foot为正在加载更多。3，当前数据必须超过一屏（即可滑动状态） ）
        int viewType=getItemViewType(position);
        if(viewType==LOAD_MORE_VIEW&&position==data.size()&& tagFullScreen) {
            if (getRecyclerView() != null) {
                getRecyclerView().post(new Runnable() {
                    @Override
                    public void run() {
                        if(iOnLoadMoreListener!=null) {
                            iOnLoadMoreListener.onLoadMore();
                        }
                    }
                });
            } else {
                if(iOnLoadMoreListener!=null) {
                    iOnLoadMoreListener.onLoadMore();
                }
            }
        }
    }


    /**
     * 设置gridview look up size
     */
    private void setLookUpSize() {
        if (recyclerView != null) {
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager) {
                final int spanCount = ((GridLayoutManager) manager).getSpanCount();
                ((GridLayoutManager) manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int i) {
                        //有底部布局
                        if (loadType != CONTENT_VIEW) {
                            if(data!=null&&data.size()>0&&i==data.size()) {
                                return spanCount;
                            }else if(data==null||data.size()==0) {
                                return spanCount;
                            }else {
                                return 1;
                            }
                        } else {
                            return 1;
                        }
                    }
                });
            }
        }
    }

    /**
     * check if full page after {@link #setList(List)}, if full, it will enable load more again.
     * <p>
     * 不是配置项！！
     * <p>
     * 这个方法是用来检查是否满一屏的，所以只推荐在 {@link #setList(List)} (List)} 之后使用
     * 原理很简单，先关闭 load more，检查完了再决定是否开启
     * <p>
     * 不是配置项！！
     *
     * @param recyclerView your recyclerView
     * @see #setList(List)
     */
    private void isFullPage(RecyclerView recyclerView, final ICheckFullPage iCheckFullPage) {
        if (iCheckFullPage == null) {
            return;
        }
        if (recyclerView == null) {
            iCheckFullPage.checkFullPage(false);
            return;
        }
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager == null) {
            iCheckFullPage.checkFullPage(false);
            return;
        }
        if (manager instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isFullScreen(linearLayoutManager)) {
                        iCheckFullPage.checkFullPage(true);
                    }else {
                        iCheckFullPage.checkFullPage(false);
                    }
                }
            }, 50);
        } else if (manager instanceof StaggeredGridLayoutManager) {
            final StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) manager;
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    final int[] positions = new int[staggeredGridLayoutManager.getSpanCount()];
                    staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(positions);
                    int pos = getTheBiggestNumber(positions) + 1;
                    if (pos != getItemCount()) {
                        iCheckFullPage.checkFullPage(true);
                    }else {
                        iCheckFullPage.checkFullPage(false);
                    }
                }
            }, 50);
        }

    }


    private boolean isFullScreen(LinearLayoutManager llm) {
        return (llm.findLastCompletelyVisibleItemPosition() + 1) != getItemCount() ||
                llm.findFirstCompletelyVisibleItemPosition() != 0;
    }

    private int getTheBiggestNumber(int[] numbers) {
        int tmp = -1;
        if (numbers == null || numbers.length == 0) {
            return tmp;
        }
        for (int num : numbers) {
            if (num > tmp) {
                tmp = num;
            }
        }
        return tmp;
    }
}