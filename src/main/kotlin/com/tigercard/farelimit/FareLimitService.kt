package com.tigercard.farelimit

import com.tigercard.entity.Journey
import com.tigercard.entity.Route
import com.tigercard.farelimit.dao.FareLimitDaoFactory
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields

class FareLimitService(
    private val fareLimitDaoFactory: FareLimitDaoFactory,
) {
    fun getFareLimit(
        route: Route,
        fareLimitType: FareLimitType
    ): Double {
        val fareLimitDao = fareLimitDaoFactory.getFareLimitByType(fareLimitType)

        return fareLimitDao.getFareLimit(route)
    }

    fun getFareLimitByDay(journeyList: List<Journey>): Map<String, Double> {
        val journeyByDate = journeyList.groupBy { it.journeyTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) }

        return findFareByType(journeyByDate, FareLimitType.DAY)
    }

    fun getFareLimitByWeek(journeyList: List<Journey>): Map<String, Double> {
        val journeyByWeek =
            journeyList.groupBy { "${it.journeyTime.year}-${it.journeyTime.get(WeekFields.ISO.weekOfYear())}" }

        return findFareByType(journeyByWeek, FareLimitType.WEEK)
    }

    private fun findFareByType(
        journeyByDate: Map<String, List<Journey>>,
        fareLimitType: FareLimitType
    ): Map<String, Double> {
        val uniqueRoutesByDate = journeyByDate.map { (key, routesForDate) ->
            key to routesForDate.map { Route(it.fromZone, it.toZone) }.toSet()
        }.toMap()

        val fareLimitDao = fareLimitDaoFactory.getFareLimitByType(fareLimitType)

        return uniqueRoutesByDate.map { (k, v) ->
            val maxFareLimitForType =
                v.map { fareLimitDao.getFareLimit(it) }.maxOrNull() ?: error("No fare limit found")

            k to maxFareLimitForType
        }.toMap()
    }
}