package com.dedechandran.quranapps.common

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDate(format: String = "EEEE, dd MMMM yyyy, hh:mm a", locale: String = "id"): String {
    val date = Date(this)
    val simpleDateFormat = SimpleDateFormat(format, Locale(locale,locale))
    return simpleDateFormat.format(date)
}