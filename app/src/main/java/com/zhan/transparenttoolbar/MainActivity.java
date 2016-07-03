package com.zhan.transparenttoolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.zhan.transparenttoolbar.widget.MyScrollView;
import com.zhan.transparenttoolbar.widget.TransparentToolBar;

public class MainActivity extends AppCompatActivity implements TransparentToolBar.OnScrollStateListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TransparentToolBar mTransparentToolBar;
    private MyScrollView mMyScrollView;
    private TextView mHeadTv;
    private int mHeadValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mTransparentToolBar.setOnScrollStateListener(this);
        mHeadTv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeadValue = mHeadTv.getMeasuredHeight();

                //必须设置ToolBar最大偏移量，这会影响到0~1透明度变化的范围
                mTransparentToolBar.setOffset(mHeadValue);

                mHeadTv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Log.i(TAG, "mHeadValue:" + mHeadValue);
            }
        });
    }

    private void initData() {

        //必须设置ToolBar颜色值，也就是0~1透明度变化的颜色值
        mTransparentToolBar.setBgColor(getResources().getColor(R.color.bar_color));

        mMyScrollView.setTitleBar(mTransparentToolBar);
    }

    private void initView() {
        mTransparentToolBar = (TransparentToolBar) findViewById(R.id.main_bar);
        mMyScrollView = (MyScrollView) findViewById(R.id.main_sv_root);
        mHeadTv = (TextView) findViewById(R.id.main_head);
    }

    @Override
    public void updateFraction(float fraction) {
        //ToolBar滚动回调的百分比0~1
    }
}
