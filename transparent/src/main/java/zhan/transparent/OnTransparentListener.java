package zhan.transparent;


public interface OnTransparentListener {
  void onTransparentStart(float fraction);

  void onTransparentEnd(float fraction);

  void onTransparentUpdateFraction(float fraction);
}
