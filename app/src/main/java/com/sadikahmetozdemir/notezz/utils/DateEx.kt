package com.sadikahmetozdemir.notezz.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.toDateString(): String {
    val formatter = SimpleDateFormat("MM'/'dd'/'y hh:mm")
    return formatter.format(this)
}
