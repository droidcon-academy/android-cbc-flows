package com.example.differentflowswithcompose.data

import android.util.Log
import com.example.differentflowswithcompose.data.dao.EventDAO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

class EventManager {
    private val tag = EventManager::class.java.canonicalName

    private val eventStarted = MutableStateFlow(false)
    private val _events = MutableSharedFlow<EventDAO>(10, 5)
    val events = _events.asSharedFlow()

    companion object {
        private const val DELAY_TO_SIMULATE_EVENT_DURATION = 5_000L
    }

    suspend fun startEvent() {
        eventStarted.value = true
        Log.v(tag, "Starting an event")
        //Emitting all events
        allEvents.forEachIndexed { index, eventDAO ->
            delay(DELAY_TO_SIMULATE_EVENT_DURATION)

            // Condition to emit only if event is not ended in the meantime by calling endEvent()
            if (eventStarted.value) {
                Log.v(tag, "Emitting events ${index.plus(1)}")
                _events.emit(eventDAO)
            }
        }
        Log.v(tag, "All events are emitted")
        endEvent()
    }

    fun endEvent() {
        eventStarted.value = false
    }
}