package com.example.differentflowswithcompose.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.differentflowswithcompose.data.EventRepository
import com.example.differentflowswithcompose.ui.EventViewModel

class EventViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventViewModel(EventRepository()) as T
    }

}
