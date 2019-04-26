package com.iriasan.gotbets.core.extension

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

fun ConstraintLayout.showAllViewsAnimated() {
    for (i in 0 until this.childCount) {
        val child = this.getChildAt(i)
        if (child is View) {
            child.show(i)
        }
    }
}