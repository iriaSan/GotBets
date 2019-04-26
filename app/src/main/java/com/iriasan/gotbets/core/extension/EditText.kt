package com.iriasan.gotbets.core.extension

import android.os.CountDownTimer
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View.OnFocusChangeListener
import android.widget.TextView
import com.iriasan.gotbets.core.DecimalDigitsInputFilter

fun TextView.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun TextView.afterFocusChanged(afterFocusChanged: (String) -> Unit) {
    val text = this.text.toString()
    this.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
        if (this == view && !hasFocus) {
            afterFocusChanged.invoke(text)
        }
    }
}

fun TextView.afterFocusChangedHasFocus(focus: (Boolean) -> Unit) {
    this.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
        focus.invoke(hasFocus)
    }
}

/*If you have a edittext that in xml you was make no focusable, you need use this function to you need to use this to make the edittext normal but not opened.*/
/*Add this in the root layout--> android:focusable="true" android:focusableInTouchMode="true" android:fitsSystemWindows="true"*/
fun TextView.makeFocusableAndClearFocus() {
    this.isFocusableInTouchMode = false
    this.isFocusable = false
    this.isFocusableInTouchMode = true
    this.isFocusable = true
}

fun TextView.afterTextChangedDelayed(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        var timer: CountDownTimer? = null

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            timer?.cancel()
            timer = object : CountDownTimer(1000, 1500) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    afterTextChanged.invoke(editable.toString())
                }
            }.start()
        }
    })
}

fun TextView.afterTextChangedForCurrency(value: (Double) -> Unit) {
    this@afterTextChangedForCurrency.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(9, 1))
    this.addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {

            try {

                val valueFormated = java.lang.Double.parseDouble(editable.toString().replace(',', '.'))

                // formated.invoke(editable.toString())
                value.invoke(valueFormated)
            } catch (e: NumberFormatException) {
                // Error
            }
        }
    })
}