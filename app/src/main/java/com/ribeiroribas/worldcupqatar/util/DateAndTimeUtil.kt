package com.ribeiroribas.worldcupqatar.util

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun getEventDates(): MutableList<LocalDate> {
    val eventDates: MutableList<LocalDate> = arrayListOf()
    val finalEventDate = LocalDate.of(2022, 12, 19)
    var eventDate = LocalDate.of(2022, 11, 20)
    do {
        eventDates.add(eventDate)
        eventDate = eventDate.plusDays(1)
    } while (eventDate.isBefore(finalEventDate))
    return eventDates
}

fun formatLocalDate(evenDate: LocalDate?, format: String): String? {
    evenDate?.let {
        val df: DateTimeFormatter = DateTimeFormatter.ofPattern(format)
        return df.format(evenDate)
    }
    return null
}

fun formatLocalTime(eventTime: LocalTime?): String? {
    eventTime?.let {
        val df = DateTimeFormatter.ofPattern("H:mm")
        return eventTime.format(df)
    }
    return null
}

