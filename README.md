
<!--lang: java-->
####效果如图：

![](https://github.com/ruzhan123/TransparentToolBar/raw/master/gif/toolbar01.gif)

</br>

我的博客：[详解](https://ruzhan123.github.io/2016/07/03/2016-07-03-19-TransparentToolBar/)


TransparentToolBar 一个简单随着滚动控件滚动，不断改变颜色透明度的ToolBar。核心类就一个：TransparentToolBar.java


使用方式如下：



1，把TransparentToolBar.java拷贝到你的工程里，布局文件中直接使用：


TransparentToolBar是RelativeLayout的子类，里面想放什么子View随意。


```java

	    <zhan.transparent.widget.TransparentToolBar
	        android:id="@+id/main_bar"
	        android:layout_width="match_parent"
	        android:layout_height="55dp">
	
	        <TextView
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content"
	            android:layout_centerInParent="true"
	            android:gravity="center"
	            android:text="Transparent toolbar"
	            android:textColor="@android:color/white"
	            android:textSize="16sp" />
	
	        <View
	            android:layout_width="match_parent"
	            android:layout_height="1px"
	            android:layout_alignParentBottom="true"
	            android:background="@android:color/black" />
	
	    </zhan.transparent.widget.TransparentToolBar>


```


2，找到TransparentToolBar对象，设置最大偏移量与背景色(必须设置这两个参数):

setBgColor  为ToolBar背景颜色，必须设置。透明度变化为0~1

setOffset   为ToolBar滚动控件滚动，ToolBar透明度变化范围的计算参数。top/offset = 0~1

```java
	
		mTransparentToolBar = (TransparentToolBar) findViewById(R.id.main_bar);
	
	
	    //必须设置ToolBar颜色值，也就是0~1透明度变化的颜色值
	    mTransparentToolBar.setBgColor(getResources().getColor(R.color.bar_color));
	
	    //必须设置ToolBar最大偏移量，这会影响到0~1透明度变化的范围
	    mTransparentToolBar.setOffset(mHeadValue);


```

3，设置滚动控件的setOnScrollChangeListener或者在自定义里复写onScrollChanged方法，为了获取实时的滚动高度top值

一定要把滚动控件的实时高度传递给ToolBar。

```java

		setOnScrollChangeListener
		
		//一定要设置进去
		mTransparentToolBar.setChangeTop(t);

```

或者在自定义View里：

```java


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


```

4，可以设置ToolBar setOnScrollStateListener，获取ToolBar跟随滚动控件计算出的百分比0~1，用于同步一些View动画

```java

		mTransparentToolBar.setOnScrollStateListener(this);
		


	    @Override
	    public void updateFraction(float fraction) {
	        //ToolBar滚动回调的百分比0~1
	    }


```

现在可以使用TransparentToolBar了。

---


工程的Demo中是获取某一个View的高度，用于设置ToolBar最大偏移量offset。如果需要，可以参考：



```java

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


```

如果你想让这个View做更多的事，或者优化的更好，可以自行修改TransparentToolBar.java

