package com.example.differentflowswithcompose.data


import com.example.differentflowswithcompose.data.dao.EventDAO
import java.util.Calendar
import java.util.Date


val allEvents = listOf(
    EventDAO(
        "Keynote: Mobile Tech Future",
        "John Smith",
        createCalendar(2023, 10, 15, 9, 0),
        createCalendar(2023, 10, 15, 10, 0)
    ),
    EventDAO(
        "App Dev Best Practices",
        "Jane Doe",
        createCalendar(2023, 10, 15, 10, 30),
        createCalendar(2023, 10, 15, 11, 30)
    ),
    EventDAO(
        "UX Design in Mobile Apps",
        "Michael Brown",
        createCalendar(2023, 10, 15, 12, 0),
        createCalendar(2023, 10, 15, 13, 0)
    ),
    EventDAO(
        "5G in Mobile Innovation",
        "Emily White",
        createCalendar(2023, 10, 15, 14, 0),
        createCalendar(2023, 10, 15, 15, 0)
    ),
    EventDAO(
        "App Monetization Strategies",
        "David Clark",
        createCalendar(2023, 10, 15, 15, 30),
        createCalendar(2023, 10, 15, 16, 30)
    ),
    EventDAO(
        "Mobile Gaming Trends",
        "Sarah Green",
        createCalendar(2023, 10, 16, 9, 0),
        createCalendar(2023, 10, 16, 10, 0)
    ),
    EventDAO(
        "Cross-Platform Dev",
        "Kevin Anderson",
        createCalendar(2023, 10, 16, 10, 30),
        createCalendar(2023, 10, 16, 11, 30)
    ),
    EventDAO(
        "Mobile Marketing & Promotion",
        "Laura Taylor",
        createCalendar(2023, 10, 16, 12, 0),
        createCalendar(2023, 10, 16, 13, 0)
    ),
    EventDAO(
        "Mobile Security & Privacy",
        "Mark Johnson",
        createCalendar(2023, 10, 16, 14, 0),
        createCalendar(2023, 10, 16, 15, 0)
    ),
    EventDAO(
        "Panel: Mobile Ecosystem",
        "Panel of Experts",
        createCalendar(2023, 10, 16, 15, 30),
        createCalendar(2023, 10, 16, 16, 30)
    )
)

private fun createCalendar(year: Int, month: Int, day: Int, hour: Int, minute: Int): Date {
    return Calendar.getInstance().apply {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month - 1) // Calendar months are 0-based
        set(Calendar.DAY_OF_MONTH, day)
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
    }.time
}
