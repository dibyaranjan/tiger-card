package com.tigercard.surcharge

import com.tigercard.surcharge.dao.SurchargeDao
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SurchargeService(
    private val surchargeDao: SurchargeDao
) {
    fun getSurcharge(journeyDateTime: LocalDateTime): Double {
        val dayOfWeek = journeyDateTime.dayOfWeek
        val timeAsInteger = journeyDateTime.format(DateTimeFormatter.ofPattern("HHmm")).toInt()

        val surchargeType = when {
            isWeekdayPeakHours(dayOfWeek, timeAsInteger) -> SurchargeType.WEEKDAY_PEAK
            isWeekendPeakHours(dayOfWeek, timeAsInteger) -> SurchargeType.WEEKEND_PEAK
            else -> SurchargeType.NON_PEAK
        }

        return surchargeDao.getSurcharge(surchargeType)
    }

    private fun isWeekdayPeakHours(dayOfWeek: DayOfWeek, time: Int) =
        dayOfWeek in DayOfWeek.MONDAY..DayOfWeek.FRIDAY && (time in 700..1030 || time in 1700..2000)

    private fun isWeekendPeakHours(dayOfWeek: DayOfWeek, time: Int) =
        dayOfWeek in DayOfWeek.SATURDAY..DayOfWeek.SUNDAY && (time in 900..1100 || time in 1800..2200)
}