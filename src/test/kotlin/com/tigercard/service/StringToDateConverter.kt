package com.tigercard.service

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME

fun createJourneyTime(
    dateTime: String
): LocalDateTime = LocalDateTime.parse(dateTime, ISO_LOCAL_DATE_TIME)