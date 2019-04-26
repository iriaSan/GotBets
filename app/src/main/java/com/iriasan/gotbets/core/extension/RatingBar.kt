package com.iriasan.gotbets.core.extension

import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener

fun RatingBar.onRatingBarChange(onRatingChanged: (Float) -> Unit) {
    this.onRatingBarChangeListener = OnRatingBarChangeListener { _, rating, _ -> onRatingChanged.invoke(rating) }
}
