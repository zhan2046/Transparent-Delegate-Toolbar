package com.zhan.transparenttoolbar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mData = ArrayList<Any>()

    init {
        mData.add("")
        for (x in 0..DEFAULT_SIZE) {
            mData.add(x)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val `object` = mData[position]
        return if (`object` is String) {
            TYPE_HEADER
        } else TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (TYPE_HEADER == viewType) {
            MyHeaderHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.header, parent, false))

        } else MyHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // do nothing
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    companion object {

        private const val TYPE_NORMAL = 10000
        private const val TYPE_HEADER = 2000

        private const val DEFAULT_SIZE = 32
    }
}
