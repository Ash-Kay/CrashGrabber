package io.ashkay.crashgrabber.internal.utils

import java.text.SimpleDateFormat
import java.util.Locale

const val DATE_TIME_PATTERN = "dd-M-yyyy hh:mm:ss"
const val TIME_PATTERN = "hh:mm:ss"

internal fun Long.toDateTime(): String {
    return SimpleDateFormat(DATE_TIME_PATTERN, Locale.getDefault()).format(this)
}

internal fun Long.toTime(): String {
    return SimpleDateFormat(TIME_PATTERN, Locale.getDefault()).format(this)
}