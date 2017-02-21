
Transparent-Delegate-Toolbar
===============

A simple transparent toolbar for Android

Screenshots
===============

![](https://github.com/ruzhan123/Transparent-Delegate-Toolbar/raw/master/gif/toolbar.gif)


Transparent-Delegate-Toolbar use **Delegate** finish scroll change color to alpha

[![](https://jitpack.io/v/ruzhan123/Transparent-Delegate-Toolbar.svg)](https://jitpack.io/#ruzhan123/Transparent-Delegate-Toolbar)


Gradle
------

Add it in your root build.gradle at the end of repositories:


```java

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency:


```java

	dependencies {
	        compile 'com.github.ruzhan123:Transparent-Delegate-Toolbar:v1.0'
	}
```


Usage
------

```xml

    <zhan.transparent.widget.TransparentFrameLayout
        android:id="@+id/tool_bar"
        android:layout_width="match_parent"
        android:layout_height="48dp">
```

```java

    mTransparentFrameLayout.setColorToBackGround(getResources().getColor(R.color.colorPrimary));
    mTransparentFrameLayout.setMaxOffset(getResources().getDimension(R.dimen.offset));
```

```java

    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mDy += dy;
        mTransparentFrameLayout.updateTop(mDy);
      }
    });
```

Delegate
------

 <ul>
   	<li><a href='javascript:'>ITransparentDelegate</a></li>
   	<li><a href='javascript:'>TransparentDelegate</a></li>
 </ul>

Implementing View
------

 <ul>
   	<li><a href='javascript:'>TransparentFrameLayout</a></li>
   	<li><a href='javascript:'>TransparentLinearLayout</a></li>
	<li><a href='javascript:'>TransparentRelativeLayout</a></li>
	<li><a href='javascript:'>TransparentToolBar</a></li>
 </ul>

Custom
------

```java

	public class TransparentToolBar extends Toolbar implements ITransparentDelegate {
	
	private TransparentDelegate mTransparentDelegate;
	
	public TransparentToolBar(Context context) {
	super(context);
	init();
	}
	
	public TransparentToolBar(Context context, AttributeSet attrs) {
	super(context, attrs);
	init();
	}
	
	public TransparentToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
	super(context, attrs, defStyleAttr);
	init();
	}
	
	private void init() {
	mTransparentDelegate = new TransparentDelegate(this);
	}
	
	@Override public void updateTop(float top) {
	mTransparentDelegate.updateTop(top);
	}
	
	@Override public void setMaxOffset(float offset) {
	mTransparentDelegate.setMaxOffset(offset);
	}
	
	@Override public void setColorToBackGround(int backGroundColor) {
	mTransparentDelegate.setColorToBackGround(backGroundColor);
	}

	...
```

expand
------

```java

	public interface OnTransparentListener {
	  void onTransparentStart(float fraction);	//scroll to top, offset is 0
	
	  void onTransparentEnd(float fraction);	//scroll to max offset
	
	  void onTransparentUpdateFraction(float fraction);	//scrolling...
	}
```

Developed by
-------

 ruzhan - <a href='javascript:'>dev19921116@gmail.com</a>


License
-------

    Copyright 2017 ruzhan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
	
