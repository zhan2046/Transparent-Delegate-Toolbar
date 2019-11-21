package zhan.transparent

import android.graphics.Color
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.content.ContextCompat
import java.util.*


class TransparentDelegate(private val mRootView: View) : ViewTreeObserver.OnGlobalLayoutListener,
        ITransparentDelegate {
    private val mOnTransparentListeners = ArrayList<OnTransparentListener>()

    private var offset: Float = 0.toFloat()
    private val mNormalGroundColor: Int = ContextCompat.getColor(mRootView.context,
            android.R.color.transparent)
    private var mBackGroundColor: Int = 0

    init {
        mBackGroundColor = mNormalGroundColor
        mRootView.viewTreeObserver.removeGlobalOnLayoutListener(this)
    }

    override fun onGlobalLayout() {
        mRootView.setBackgroundColor(mBackGroundColor)
        mRootView.viewTreeObserver.removeGlobalOnLayoutListener(this)
    }

    fun addOnScrollStateListener(listener: OnTransparentListener?) {
        if (listener != null) {
            if (!mOnTransparentListeners.contains(listener)) {
                mOnTransparentListeners.add(listener)
            }
        }
    }

    fun removeOnScrollStateListener(listener: OnTransparentListener?) {
        if (listener != null) {
            if (mOnTransparentListeners.contains(listener)) {
                mOnTransparentListeners.remove(listener)
            }
        }
    }

    fun removeOnScrollStateListenerAll() {
        mOnTransparentListeners.clear()
    }

    private fun changeAlpha(color: Int, fraction: Float): Int {
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        val alpha = (Color.alpha(color) * fraction).toInt()
        return Color.argb(alpha, red, green, blue)
    }

    override fun updateTop(top: Float) {
        if (offset <= 0 && mBackGroundColor == mNormalGroundColor) {
            return
        }
        var fraction = top / offset

        if (fraction <= MIN_FRACTION) {
            fraction = MIN_FRACTION.toFloat()
            for (listener in mOnTransparentListeners) {
                listener.onTransparentStart(fraction)
            }
        }
        if (fraction >= MAX_FRACTION) {
            fraction = MAX_FRACTION.toFloat()
            for (listener in mOnTransparentListeners) {
                listener.onTransparentEnd(fraction)
            }
        }
        val newColor = changeAlpha(mBackGroundColor, fraction)
        mRootView.setBackgroundColor(newColor)

        for (listener in mOnTransparentListeners) {
            listener.onTransparentUpdateFraction(fraction)
        }
    }

    override fun setMaxOffset(offset: Float) {
        this.offset = offset
    }

    override fun setColorToBackGround(backGroundColor: Int) {
        this.mBackGroundColor = backGroundColor
    }

    fun getColorToBackGround(): Int {
        return mBackGroundColor
    }

    companion object {

        private const val MIN_FRACTION = 0
        private const val MAX_FRACTION = 1
    }
}
