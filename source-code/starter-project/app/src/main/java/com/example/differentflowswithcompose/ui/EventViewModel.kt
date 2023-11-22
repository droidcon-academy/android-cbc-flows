package com.example.differentflowswithcompose.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.differentflowswithcompose.data.EventRepository
import com.example.differentflowswithcompose.domain.adapter.toEventDto
import com.example.differentflowswithcompose.domain.dto.EventDTO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EventViewModel(private val repository: EventRepository) : ViewModel() {

    private val tag = EventViewModel::class.java.canonicalName

    private val _totalAttendees = MutableStateFlow(0)
    val totalAttendees = _totalAttendees.asStateFlow()

    private val _events = MutableStateFlow<List<EventDTO>>(emptyList())
    val events = _events.asStateFlow()

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
        viewModelScope.launch {
        }
    }

    fun joinEvent(participantId: Int) {
    }

    fun leaveEvent(participantId: Int) {
    }

    fun endEvent() {
    }

    private fun getEvents() {
        viewModelScope.launch {
        }
    }
}