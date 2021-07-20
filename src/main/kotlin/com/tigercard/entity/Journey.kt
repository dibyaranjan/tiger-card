package com.tigercard.entity

import java.time.LocalDateTime

data class Journey(
    val journeyTime: LocalDateTime,
    val fromZone: Zone,
    val toZone: Zone
)