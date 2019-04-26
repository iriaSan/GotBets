package com.iriasan.gotbets.core.extension

import android.widget.SeekBar

fun SeekBar.onSeekBarChange(minLimit: Int, onProgressChanged: (Int) -> Unit) {
    this.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            when {
                progress < minLimit -> onProgressChanged.invoke(minLimit)
                else -> onProgressChanged.invoke(progress)
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    })
}