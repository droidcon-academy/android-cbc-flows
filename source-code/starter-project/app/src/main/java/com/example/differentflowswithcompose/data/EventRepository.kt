package com.example.differentflowswithcompose.data

import com.example.differentflowswithcompose.data.dao.EventDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flowOf

class EventRepository {

    suspend fun startEvent() {
        TODO("Implementation missing")
    }

    fun getEvents(): SharedFlow<EventDAO> {
        TODO("Implementation missing")
    }

    fun getTotalAttendees(): Flow<Int> {
        TODO("Implementation missing")
    }

    fun joinEvent(participantId: Int) {
        TODO("Implementation missing")
    }

    fun leaveEvent(participantId: Int) {
        TODO("Implementation missing")
    }

    fun endEvent() {
        TODO("Implementation missing")
    }
}