package com.tigercard.entity

data class Zone(
    val zoneId: Int
) {
    companion object {
        val ZONE_ONE = Zone(1)
        val ZONE_TWO = Zone(2)
    }
}