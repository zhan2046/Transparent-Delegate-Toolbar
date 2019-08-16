package com.zhan.transparenttoolbar;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter {

  private static final int TYPE_NORMAL = 10000;
  private static final int TYPE_HEADER = 2000;

  private MyHeaderHolder mMyHeaderHolder;

  private List<Object> mData = new ArrayList<>();

  public MyAdapter() {
    mData.add("");
    for (int x = 0; x < 33; x++) {
      mData.add(x);
    }
  }

  public int getHeaderHeight() {
    if (mMyHeaderHolder != null) {
      return mMyHeaderHolder.getHeaderHeight();
    }
    return -1;
  }

  @Override public int getItemViewType(int position) {
    Object object = mData.get(position);
    if (object instanceof String) {
      return TYPE_HEADER;
    }
    return TYPE_NORMAL;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (TYPE_HEADER == viewType) {
      return new MyHeaderHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false));
    }
    return new MyHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    int viewType = getItemViewType(position);
    if (TYPE_HEADER == viewType) {
      ((MyHeaderHolder) holder).bind();
    } else if (TYPE_NORMAL == viewType) {
      ((MyHolder) holder).bind();
    }
  }

  @Override public int getItemCount() {
    return mData == null ? 0 : mData.size();
  }
}
