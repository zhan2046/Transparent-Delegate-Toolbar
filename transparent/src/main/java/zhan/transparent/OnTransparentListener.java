package zhan.transparent;

/**
 * Created by zhan on 2017/2/14.
 */

public interface OnTransparentListener {
  void onTransparentStart(float fraction);

  void onTransparentEnd(float fraction);

  void onTransparentUpdateFraction(float fraction);
}
