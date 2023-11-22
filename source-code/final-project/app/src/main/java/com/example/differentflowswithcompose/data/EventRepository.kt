package com.example.differentflowswithcompose.data

import com.example.differentflowswithcompose.data.dao.EventDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flowOf

class EventRepository {
    private val totalAttendees = mutableSetOf<Int>()


    private val eventManager = EventManager()
    suspend fun startEvent() {
        eventManager.startEvent()
    }

    fun getEvents(): SharedFlow<EventDAO> {
        return eventManager.events
    }

    fun getTotalAttendees(): Flow<Int> {
        return flowOf(totalAttendees.size)
    }

    fun joinEvent(participantId: Int) {
        totalAttendees.add(participantId)
    }

    fun leaveEvent(participantId: Int) {
        totalAttendees.remove(participantId)
    }

    fun endEvent() {
        eventManager.endEvent()
    }
}