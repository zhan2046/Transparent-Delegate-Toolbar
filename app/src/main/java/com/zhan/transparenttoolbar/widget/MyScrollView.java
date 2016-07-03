package com.zhan.transparenttoolbar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by hrz on 2016/7/3.
 */
public class MyScrollView extends ScrollView {

    private TransparentToolBar mTransparentToolBar;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //将滚动不断变化的top值传递给ToolBar用于计算透明度
        mTransparentToolBar.setChangeTop(t);
    }

    /** 注入ToolBar */
    public void setTitleBar(TransparentToolBar titleBar) {
        mTransparentToolBar = titleBar;
    }
}
