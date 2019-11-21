package zhan.transparent


interface OnTransparentListener {
    fun onTransparentStart(fraction: Float)

    fun onTransparentEnd(fraction: Float)

    fun onTransparentUpdateFraction(fraction: Float)
}
