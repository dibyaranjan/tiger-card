package com.tigercard.farelimit

import com.tigercard.JourneyListTestData
import com.tigercard.entity.Route
import com.tigercard.entity.Zone
import com.tigercard.farelimit.dao.FareLimitDaoFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class FareLimitServiceTest {
    private var fareLimitService: FareLimitService = FareLimitService(FareLimitDaoFactory())

    @Test
    fun `test day limit request for zone 1 to zone 1 travel`() {
        val fareLimit = fareLimitService.getFareLimit(Route(Zone(1), Zone(1)), FareLimitType.DAY)

        Assertions.assertEquals(100.0, fareLimit)
    }

    @Test
    fun `test day limit request for zone 1 to zone 2 travel`() {
        val fareLimit = fareLimitService.getFareLimit(Route(Zone(1), Zone(2)), FareLimitType.DAY)

        Assertions.assertEquals(120.0, fareLimit)
    }

    @Test
    fun `test day limit request for zone 2 to zone 1 travel`() {
        val fareLimit = fareLimitService.getFareLimit(Route(Zone(2), Zone(1)), FareLimitType.DAY)

        Assertions.assertEquals(120.0, fareLimit)
    }

    @Test
    fun `test day limit request for zone 2 to zone 2 travel`() {
        val fareLimit = fareLimitService.getFareLimit(Route(Zone(2), Zone(2)), FareLimitType.DAY)

        Assertions.assertEquals(80.0, fareLimit)
    }

    @Test
    fun `test week limit request for zone 1 to zone 1 travel`() {
        val fareLimit = fareLimitService.getFareLimit(Route(Zone(1), Zone(1)), FareLimitType.WEEK)

        Assertions.assertEquals(500.0, fareLimit)
    }

    @Test
    fun `test week limit request for zone 1 to zone 2 travel`() {
        val fareLimit = fareLimitService.getFareLimit(Route(Zone(1), Zone(2)), FareLimitType.WEEK)

        Assertions.assertEquals(600.0, fareLimit)
    }

    @Test
    fun `test week limit request for zone 2 to zone 1 travel`() {
        val fareLimit = fareLimitService.getFareLimit(Route(Zone(2), Zone(1)), FareLimitType.WEEK)

        Assertions.assertEquals(600.0, fareLimit)
    }

    @Test
    fun `test week limit request for zone 2 to zone 2 travel`() {
        val fareLimit = fareLimitService.getFareLimit(Route(Zone(2), Zone(2)), FareLimitType.WEEK)

        Assertions.assertEquals(400.0, fareLimit)
    }

    @Test
    fun `when journey has two days fare limit must be returned for two days`() {
        val fareLimitByDate = fareLimitService.getFareLimitByDay(JourneyListTestData.TWO_DAY_TRIP_OVER_DAY_LIMIT)

        Assertions.assertEquals(2, fareLimitByDate.size)
        Assertions.assertEquals(100.0, fareLimitByDate["2021-07-12"])
        Assertions.assertEquals(100.0, fareLimitByDate["2021-07-13"])
    }

    @Test
    fun `when journey has one cross zone day fare limit must be returned correctly`() {
        val fareLimitByDate = fareLimitService.getFareLimitByDay(JourneyListTestData.TWO_DAY_TRIP_MULTI_ZONE_UNDER_LIMIT)

        Assertions.assertEquals(2, fareLimitByDate.size)
        Assertions.assertEquals(100.0, fareLimitByDate["2021-07-12"])
        Assertions.assertEquals(120.0, fareLimitByDate["2021-07-13"])
    }

    @Test
    fun `when journey has two weeks week limits must be returned correctly`() {
        val fareLimitByWeek = fareLimitService.getFareLimitByWeek(JourneyListTestData.TWO_WEEK_TRIP_UNDER_DAY_LIMIT_UNDER_WEEK_LIMIT_MULTI_ZONE)

        Assertions.assertEquals(2, fareLimitByWeek.size)
        Assertions.assertEquals(500.0, fareLimitByWeek["2021-28"])
        Assertions.assertEquals(600.0, fareLimitByWeek["2021-29"])
    }

}