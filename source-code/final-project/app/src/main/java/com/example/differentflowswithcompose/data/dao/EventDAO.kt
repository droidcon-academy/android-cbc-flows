package com.example.differentflowswithcompose.data.dao

import java.util.Date

data class EventDAO(
    val eventName: String,
    val eventHost: String,
    val eventStartTime: Date,
    val eventEndTime: Date
)