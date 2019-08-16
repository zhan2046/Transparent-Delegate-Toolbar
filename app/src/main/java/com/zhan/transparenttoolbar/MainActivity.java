package com.zhan.transparenttoolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import zhan.transparent.OnTransparentListener;
import zhan.transparent.widget.TransparentFrameLayout;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();

  private TransparentFrameLayout mTransparentFrameLayout;
  private RecyclerView mRecyclerView;
  private TextView numTv;

  private ImageView picIv;
  private TextView titleTv;
  private View line;

  private MyAdapter mMyAdapter;

  private int mDy;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
    initData();
    initListener();
  }

  private void initData() {
    LinearLayoutManager manager =
        new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
    mRecyclerView.setLayoutManager(manager);

    mMyAdapter = new MyAdapter();
    mRecyclerView.setAdapter(mMyAdapter);

    mTransparentFrameLayout.setColorToBackGround(getResources().getColor(R.color.colorPrimary));
    mTransparentFrameLayout.setMaxOffset(getResources().getDimension(R.dimen.offset));
  }

  private void initListener() {
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mDy += dy;
        mTransparentFrameLayout.updateTop(mDy);
      }
    });

    mTransparentFrameLayout.addOnScrollStateListener(new OnTransparentListener() {
      @Override public void onTransparentStart(float fraction) {  //scroll to top

      }

      @Override public void onTransparentEnd(float fraction) {  //scroll to max offset
        if (titleTv.getVisibility() == View.GONE) {
          titleTv.setVisibility(View.VISIBLE);
          picIv.setVisibility(View.VISIBLE);
          line.setVisibility(View.VISIBLE);
        }
      }

      @Override public void onTransparentUpdateFraction(float fraction) {  //scrolling...
        numTv.setText(fraction * 100 + "%");

        if (titleTv.getVisibility() == View.VISIBLE && fraction < 1) {
          titleTv.setVisibility(View.GONE);
          picIv.setVisibility(View.GONE);
          line.setVisibility(View.GONE);
        }
      }
    });
  }

  private void initView() {
    mTransparentFrameLayout = (TransparentFrameLayout) findViewById(R.id.tool_bar);
    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    numTv = (TextView) findViewById(R.id.num_tv);
    titleTv = (TextView) findViewById(R.id.title_tv);
    picIv = (ImageView) findViewById(R.id.pic_iv);
    line = findViewById(R.id.line);
  }
}
