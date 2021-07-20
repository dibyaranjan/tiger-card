package com.tigercard.farelimit.rule

import com.tigercard.JourneyListTestData
import com.tigercard.farelimit.FareLimitService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class WeeklyFareLimitRuleTest {
    private lateinit var weeklyFareLimitRule: WeeklyFareLimitRule
    private lateinit var fareLimitService: FareLimitService

    @BeforeEach
    fun setup() {
        fareLimitService = mockk()
        weeklyFareLimitRule = WeeklyFareLimitRule(fareLimitService, null)
    }

    @Test
    fun `when fare is under week limit then no weekly discount should be applied`() {
        val journeyList = JourneyListTestData.ONE_DAY_TRIP_OVER_DAY_LIMIT
        every { fareLimitService.getFareLimitByWeek(journeyList) } returns mapOf("2021-28" to 400.0)

        val fareLimitedAmount = weeklyFareLimitRule.applyRule(journeyList, mapOf("2021-07-12" to 80.0))

        Assertions.assertEquals(80.0, fareLimitedAmount["2021-28"])
    }

    @Test
    fun `when fare is over weekly limit then discount should be applied`() {
        val journeyList = JourneyListTestData.TWO_WEEK_TRIP_UNDER_DAY_LIMIT_OVER_WEEK_LIMIT_MULTI_ZONE

        every { fareLimitService.getFareLimitByWeek(journeyList) } returns mapOf(
            "2021-28" to 600.0,
            "2021-29" to 500.0
        )

        val dailyFareLimitedAmount = mapOf(
            "2021-07-17" to 60.0,
            "2021-07-18" to 80.0,
            "2021-07-19" to 105.0,
            "2021-07-20" to 90.0,
            "2021-07-21" to 90.0,
            "2021-07-22" to 90.0,
            "2021-07-23" to 90.0,
            "2021-07-24" to 90.0,
            "2021-07-25" to 90.0,
        )

        val fareLimitedAmount = weeklyFareLimitRule.applyRule(journeyList, dailyFareLimitedAmount)

        Assertions.assertEquals( 140.0, fareLimitedAmount["2021-28"])
        Assertions.assertEquals( 500.0, fareLimitedAmount["2021-29"])
    }
}