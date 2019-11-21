package com.zhan.transparenttoolbar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import zhan.transparent.OnTransparentListener

class MainActivity : AppCompatActivity() {

    private var mDy: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initListener()
    }

    private fun initData() {
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.layoutManager = manager

        val mMyAdapter = MyAdapter()
        recycler_view.adapter = mMyAdapter

        tool_bar.setColorToBackGround(ContextCompat.getColor(this, R.color.colorPrimary))
        tool_bar.setMaxOffset(resources.getDimension(R.dimen.offset))
    }

    private fun initListener() {
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mDy += dy
                tool_bar.updateTop(mDy.toFloat())
            }
        })

        tool_bar.addOnScrollStateListener(object : OnTransparentListener {
            override fun onTransparentStart(fraction: Float) {  //scroll to top

            }

            override fun onTransparentEnd(fraction: Float) {  //scroll to max offset
                if (title_tv.visibility == View.GONE) {
                    title_tv.visibility = View.VISIBLE
                    pic_iv.visibility = View.VISIBLE
                    line.visibility = View.VISIBLE
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onTransparentUpdateFraction(fraction: Float) {  //scrolling...
                num_tv.text = (fraction * 100).toInt().toString() + "%"
                if (title_tv.visibility == View.VISIBLE && fraction < 1) {
                    title_tv.visibility = View.GONE
                    pic_iv.visibility = View.GONE
                    line.visibility = View.GONE
                }
            }
        })
    }
}
