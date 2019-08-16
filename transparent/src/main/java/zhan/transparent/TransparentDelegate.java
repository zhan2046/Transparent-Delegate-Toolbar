package zhan.transparent;

import android.graphics.Color;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.List;


public class TransparentDelegate
    implements ViewTreeObserver.OnGlobalLayoutListener, ITransparentDelegate {

  private static final int MIN_FRACTION = 0;
  private static final int MAX_FRACTION = 1;

  private View mRootView;
  private List<OnTransparentListener> mOnTransparentListeners = new ArrayList<>();

  private float mMaxOffset;
  private int mNormalGroundColor;
  private int mBackGroundColor;

  public TransparentDelegate(View rootView) {
    if (rootView == null) {
      throw new RuntimeException("TransparentDelegate root view is null");
    }
    mRootView = rootView;
    mNormalGroundColor = mRootView.getResources().getColor(android.R.color.transparent);
    mBackGroundColor = mNormalGroundColor;
    mRootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
  }

  @Override public void onGlobalLayout() {
    mRootView.setBackgroundColor(mBackGroundColor);
    mRootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
  }

  public void addOnScrollStateListener(OnTransparentListener listener) {
    if (listener != null) {
      if (!mOnTransparentListeners.contains(listener)) {
        mOnTransparentListeners.add(listener);
      }
    }
  }

  public void removeOnScrollStateListener(OnTransparentListener listener) {
    if (listener != null) {
      if (mOnTransparentListeners.contains(listener)) {
        mOnTransparentListeners.remove(listener);
      }
    }
  }

  public void removeOnScrollStateListenerAll() {
    mOnTransparentListeners.clear();
  }

  public int changeAlpha(int color, float fraction) {
    int red = Color.red(color);
    int green = Color.green(color);
    int blue = Color.blue(color);
    int alpha = (int) (Color.alpha(color) * fraction);
    return Color.argb(alpha, red, green, blue);
  }

  @Override public void updateTop(float top) {
    if (mMaxOffset <= 0 && mBackGroundColor == mNormalGroundColor) {
      return;
    }
    float fraction = top / mMaxOffset;

    if (fraction <= MIN_FRACTION) {
      fraction = MIN_FRACTION;
      for (OnTransparentListener listener : mOnTransparentListeners) {
        listener.onTransparentStart(fraction);
      }
    }
    if (fraction >= MAX_FRACTION) {
      fraction = MAX_FRACTION;
      for (OnTransparentListener listener : mOnTransparentListeners) {
        listener.onTransparentEnd(fraction);
      }
    }
    int newColor = changeAlpha(mBackGroundColor, fraction);
    mRootView.setBackgroundColor(newColor);

    for (OnTransparentListener listener : mOnTransparentListeners) {
      listener.onTransparentUpdateFraction(fraction);
    }
  }

  @Override public void setMaxOffset(float offset) {
    this.mMaxOffset = offset;
  }

  @Override public void setColorToBackGround(int backGroundColor) {
    this.mBackGroundColor = backGroundColor;
  }

  public float getOffset() {
    return mMaxOffset;
  }

  public int getColorToBackGround() {
    return mBackGroundColor;
  }
}
