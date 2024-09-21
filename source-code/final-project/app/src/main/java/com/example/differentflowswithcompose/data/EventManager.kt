package com.example.differentflowswithcompose.data

import android.util.Log
import com.example.differentflowswithcompose.data.dao.EventDAO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class EventManager {

    private var eventStarted = false
    private val _events = MutableSharedFlow<EventDAO>()
    val events = _events.asSharedFlow()

    companion object {
        private const val DELAY_TO_SIMULATE_EVENT_DURATION = 5_000L
    }

    suspend fun startEvent() {
        if (eventStarted.not()) {
            eventStarted = true
            allEvents.forEachIndexed { index, eventDAO ->
                delay(DELAY_TO_SIMULATE_EVENT_DURATION)
                if (eventStarted) {
                    Log.v("startEvent", "Emitting events ${index.plus(1)}")
                    _events.emit(eventDAO)
                }
            }
            endEvent()
        }
    }

    fun endEvent() {
        eventStarted = false
    }
}