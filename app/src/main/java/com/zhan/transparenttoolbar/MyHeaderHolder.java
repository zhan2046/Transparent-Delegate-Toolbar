package com.zhan.transparenttoolbar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by zhan on 2017/2/14.
 */

public class MyHeaderHolder extends RecyclerView.ViewHolder {

  private int mHeaderHeight;

  public MyHeaderHolder(final View itemView) {
    super(itemView);

    itemView.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override public void onGlobalLayout() {
            mHeaderHeight = itemView.getMeasuredHeight();
            itemView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
          }
        });
  }

  public void bind() {

  }

  public int getHeaderHeight() {
    return mHeaderHeight;
  }
}
