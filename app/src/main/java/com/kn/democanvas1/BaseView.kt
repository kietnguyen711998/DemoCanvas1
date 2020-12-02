package com.kn.democanvas1

import android.content.Context
import android.util.AttributeSet
import android.view.View

open class BaseView : View {
    @JvmField
    protected val TAG = javaClass.simpleName

    constructor(context: Context?) : super(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    protected fun getSize(measureSpec: Int, defaultSize: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        var size = 0
        when (mode) {
            MeasureSpec.AT_MOST -> {
                //size = Math.min(MeasureSpec.getSize(measureSpec), defaultSize)
                size = MeasureSpec.getSize(measureSpec).coerceAtMost(defaultSize)

            }
            MeasureSpec.EXACTLY -> {
                size = MeasureSpec.getSize(measureSpec)
            }
            MeasureSpec.UNSPECIFIED -> {
                size = defaultSize
            }
        }
        return size
    }

    protected fun dp2px(dpValue: Float): Int {
        return DisplayUtils.dp2px(context, dpValue)
    }

    protected fun sp2px(spValue: Float): Int {
        return DisplayUtils.sp2px(context, spValue)
    }
}