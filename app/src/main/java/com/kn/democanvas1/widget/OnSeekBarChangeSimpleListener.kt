package com.kn.democanvas1.widget

import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener

abstract class OnSeekBarChangeSimpleListener : OnSeekBarChangeListener {
    abstract override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean)
    override fun onStartTrackingTouch(seekBar: SeekBar) {}
    override fun onStopTrackingTouch(seekBar: SeekBar) {}
}