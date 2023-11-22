package com.example.differentflowswithcompose.domain.adapter

import com.example.differentflowswithcompose.data.dao.EventDAO
import com.example.differentflowswithcompose.domain.dto.EventDTO

fun EventDAO.toEventDto(): EventDTO {
    return EventDTO(
        eventName = eventName,
        eventHost = eventHost,
        eventTime = "Start ${eventStartTime.toString(SHORT_DATE_TIME_FORMAT)} until ${
            eventEndTime.toString(
                SHORT_DATE_TIME_FORMAT
            )
        }"
    )
}