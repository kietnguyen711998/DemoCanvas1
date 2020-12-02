package com.kn.democanvas1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log

class PercentageView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : BaseView(context, attrs, defStyleAttr) {
    private var startAngle = 0f
    private var percentageModelList: List<PercentageModel>? = null
    private var paint: Paint? = null
    private fun initPaint() {
        paint = Paint()
        paint!!.style = Paint.Style.FILL
        paint!!.isAntiAlias = true
        paint!!.isDither = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val defaultSize = dp2px(DEFAULT_SIZE.toFloat())
        var width = getSize(widthMeasureSpec, defaultSize)
        var height = getSize(heightMeasureSpec, defaultSize)
        height = width.coerceAtMost(height)
        width = height
        setMeasuredDimension(width, height)
        Log.e(TAG, "onMeasure")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.e(TAG, "onSizeChanged")
    }

    private val rect = RectF()
    override fun onDraw(canvas: Canvas) {
        if (percentageModelList == null || percentageModelList!!.isEmpty()) {
            return
        }
        var currentStartAngle = startAngle
        canvas.translate(width / 2.toFloat(), height / 2.toFloat())
        val r = (Math.min(width, height) / 2 * 0.95).toFloat()
        rect.left = -r
        rect.top = -r
        rect.right = r
        rect.bottom = r
        for (percentageModel in percentageModelList!!) {
            paint!!.color = percentageModel.color
            canvas.drawArc(rect, currentStartAngle, percentageModel.angle, true, paint!!)
            paint!!.textSize = 40F
            canvas.drawText(percentageModel.value.toString()+"%", x, y, paint!!);
            currentStartAngle += percentageModel.angle
        }
    }

    fun setStartAngle(startAngle: Float) {
        this.startAngle = startAngle
        invalidate()
    }

    fun setData(percentageModelList: List<PercentageModel>?) {
        this.percentageModelList = percentageModelList
        initData(percentageModelList)
        invalidate()
    }

    private fun initData(percentageModelList: List<PercentageModel>?) {
        if (percentageModelList == null || percentageModelList.isEmpty()) {
            return
        }
        var sumValue = 0f
        for (i in percentageModelList.indices) {
            val percentageModel = percentageModelList[i]
            sumValue += percentageModel.value
            percentageModel.color = COLORS[i % COLORS.size]
        }
        var sumAngle = 0f
        for (percentageModel in percentageModelList) {
            val per = percentageModel.value / sumValue
            percentageModel.angle = per * 360
            sumAngle += percentageModel.angle
        }
        //There may be some loss of accuracy when calculating the percentage.
        // Here you need to determine whether you need to make up the difference
        if (sumAngle < 360) {
            for (percentageModel in percentageModelList) {
                if (percentageModel.angle.toInt() != 0) {
                    percentageModel.angle = 360 - sumAngle + percentageModel.angle
                    break
                }
            }
        }
    }

    companion object {
        private val COLORS = intArrayOf(-0xd0818a, -0xe008b7, -0xbd78e, -0xb9bc0c, -0x1aea7e26, -0x7ad81c, -0xe4ff3, -0xd9fdf1)

        //The default size of View, dp
        private const val DEFAULT_SIZE = 320
    }

    init {
        initPaint()
    }
}