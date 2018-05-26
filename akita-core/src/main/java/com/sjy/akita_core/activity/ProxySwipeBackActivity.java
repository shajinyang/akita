package com.sjy.akita_core.activity;

import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;

import com.sjy.akita_core.R;
import com.sjy.akita_core.delegate.AkitaSwipeBackDelegate;

import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Created by sjy on 2018/5/25.
 */

public abstract class ProxySwipeBackActivity extends SwipeBackActivity{

    protected abstract AkitaSwipeBackDelegate setRootDelegate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer();
        getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_ALL);
    }


    private void initContainer(){
        final ContentFrameLayout contentFrameLayout=new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.akita_delegate_id);
        setContentView(contentFrameLayout);
        loadRootFragment(R.id.akita_delegate_id,setRootDelegate());
    }

    /**
     * 限制SwipeBack的条件,默认栈内Fragment数 <= 1时 , 优先滑动退出Activity , 而不是Fragment
     *
     * @return true: Activity优先滑动退出;  false: Fragment优先滑动退出
     */
    @Override
    public boolean swipeBackPriority() {
        return super.swipeBackPriority();
    }

    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
