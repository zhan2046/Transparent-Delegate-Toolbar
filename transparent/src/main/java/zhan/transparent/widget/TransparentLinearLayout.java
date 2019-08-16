package zhan.transparent.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import zhan.transparent.ITransparentDelegate;
import zhan.transparent.OnTransparentListener;
import zhan.transparent.TransparentDelegate;


public class TransparentLinearLayout extends LinearLayout implements ITransparentDelegate {

  private TransparentDelegate mTransparentDelegate;

  public TransparentLinearLayout(Context context) {
    super(context);
    init();
  }

  public TransparentLinearLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public TransparentLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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

  public void addOnScrollStateListener(OnTransparentListener listener) {
    mTransparentDelegate.addOnScrollStateListener(listener);
  }

  public void removeOnScrollStateListener(OnTransparentListener listener) {
    mTransparentDelegate.removeOnScrollStateListener(listener);
  }

  public void removeOnScrollStateListenerAll() {
    mTransparentDelegate.removeOnScrollStateListenerAll();
  }
}
