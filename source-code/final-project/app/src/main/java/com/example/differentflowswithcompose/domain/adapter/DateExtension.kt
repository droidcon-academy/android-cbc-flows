package com.example.differentflowswithcompose.domain.adapter

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val SHORT_DATE_TIME_FORMAT = "dd-MMM-yy hh:mm"
fun Date.toString(dateFormat: String): String {
    return SimpleDateFormat(dateFormat, Locale.getDefault()).format(this)
}