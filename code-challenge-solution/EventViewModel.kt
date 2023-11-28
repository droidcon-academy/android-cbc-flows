package com.example.differentflowswithcompose.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.differentflowswithcompose.data.EventRepository
import com.example.differentflowswithcompose.domain.adapter.toEventDto
import com.example.differentflowswithcompose.domain.dto.EventDTO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EventViewModel(private val repository: EventRepository) : ViewModel() {

    private val tag = EventViewModel::class.java.canonicalName

    private val _totalAttendees = MutableStateFlow(0)
    val totalAttendees = _totalAttendees.asStateFlow()

    private val _events = MutableStateFlow<List<EventDTO>>(emptyList())
    val events = _events.asStateFlow()

    private var startEventsJob: Job? = null
    private var getEventsJob: Job? = null

    sealed interface UiState {
        object EMPTY : UiState
        data class Result(val eventDTO: EventDTO) : UiState
    }

    private fun getTotalAttendees() {
        viewModelScope.launch {
            _totalAttendees.value = repository.getTotalAttendees().first()
        }
    }

    fun startEvent() {
        //Cancel any previously running job that is emitting event or has emitted any events
        cancelAllEventJobs()

        // Reset any previously rendered view items
        _events.value = emptyList()
        startEventsJob = viewModelScope.launch {
            repository.startEvent()
        }
        getEvents()
    }

    fun joinEvent(participantId: Int) {
        repository.joinEvent(participantId)
        getTotalAttendees()
    }

    fun leaveEvent(participantId: Int) {
        repository.leaveEvent(participantId)
        getTotalAttendees()
    }

    fun endEvent() {
        repository.endEvent()
        cancelAllEventJobs()
    }

    private fun cancelAllEventJobs() {
        startEventsJob?.cancel()
        getEventsJob?.cancel()
    }

    private fun getEvents() {
        getEventsJob = viewModelScope.launch {
            repository.getEvents().collect {
                Log.v(tag, "Collected an event $it")
                _events.value = _events.value + it.toEventDto()
            }
        }
    }
}