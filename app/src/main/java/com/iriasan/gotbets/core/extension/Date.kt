package com.iriasan.gotbets.core.extension

import android.content.Context
import android.text.format.DateUtils
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

fun Long.convertLongToTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return format.format(date)
}





fun currentTimeToLong(): Long {
    return System.currentTimeMillis()
}

fun convertDateToLong(date: String): Long {
    val df = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return df.parse(date).time
}

fun isYesterday(date: Long): Boolean {
    val now = Calendar.getInstance()
    val cdate = Calendar.getInstance()
    cdate.timeInMillis = date
    now.add(Calendar.DATE, -1)
    return (now.get(Calendar.YEAR) == cdate.get(Calendar.YEAR) &&
            now.get(Calendar.MONTH) == cdate.get(Calendar.MONTH) &&
            now.get(Calendar.DATE) == cdate.get(Calendar.DATE))
}

fun isSameYear(date: Long): Boolean {
    val now = Calendar.getInstance()
    val cdate = Calendar.getInstance()
    cdate.timeInMillis = date
    now.add(Calendar.DATE, 0)
    return (now.get(Calendar.YEAR) == cdate.get(Calendar.YEAR))
}
