package com.tigercard.farelimit.rule

import com.tigercard.JourneyListTestData
import com.tigercard.farelimit.FareLimitService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DailyFareLimitRuleTest {
    private lateinit var dailyFareLimitRule: DailyFareLimitRule
    private lateinit var fareLimitService: FareLimitService

    @BeforeEach
    fun setUp() {
        fareLimitService = mockk()
    }

    @Test
    fun `when fare limit is applied then fares should be discounted for a day`() {
        val journeyList = JourneyListTestData.ONE_DAY_TRIP_OVER_DAY_LIMIT

        dailyFareLimitRule = DailyFareLimitRule(fareLimitService, null)
        every { fareLimitService.getFareLimitByDay(journeyList) } returns mapOf("2021-07-12" to 100.0)

        val fareLimitedAmount = dailyFareLimitRule.applyRule(journeyList, mapOf("2021-07-12" to 80.0))

        Assertions.assertEquals(80.0, fareLimitedAmount["2021-07-12"])
    }

    @Test
    fun `when fare limit is applied then fares should be discounted multiple days`() {
        val journeyList = JourneyListTestData.TWO_DAY_TRIP_OVER_DAY_LIMIT

        dailyFareLimitRule = DailyFareLimitRule(fareLimitService, null)
        every { fareLimitService.getFareLimitByDay(journeyList) } returns mapOf(
            "2021-07-12" to 100.0,
            "2021-07-13" to 120.0
        )

        val fareLimitedAmount = dailyFareLimitRule.applyRule(
            journeyList,
            mapOf("2021-07-12" to 100.0, "2021-07-13" to 100.0)
        )

        Assertions.assertEquals(100.0, fareLimitedAmount["2021-07-12"])
        Assertions.assertEquals(100.0, fareLimitedAmount["2021-07-13"])
    }

    @Test
    fun `when weekly fare limit is also applied then summary should be converted to weekly format`() {
        val journeyList = JourneyListTestData.TWO_WEEK_TRIP_UNDER_DAY_LIMIT_OVER_WEEK_LIMIT_MULTI_ZONE

        val weeklyFareLimitRule: WeeklyFareLimitRule = mockk()

        dailyFareLimitRule = DailyFareLimitRule(fareLimitService, weeklyFareLimitRule)
        every { fareLimitService.getFareLimitByDay(journeyList) } returns mapOf(
            "2021-07-17" to 80.0,
            "2021-07-18" to 100.0,
            "2021-07-19" to 120.0,
            "2021-07-20" to 100.0,
            "2021-07-21" to 100.0,
            "2021-07-22" to 100.0,
            "2021-07-23" to 100.0,
            "2021-07-24" to 100.0,
            "2021-07-25" to 100.0,
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

        every { weeklyFareLimitRule.applyRule(journeyList, dailyFareLimitedAmount) } returns mapOf(
            "2021-28" to 140.0,
            "2021-29" to 500.0
        )

        val fareLimitedAmount = dailyFareLimitRule.applyRule(
            journeyList,
            dailyFareLimitedAmount
        )

        Assertions.assertEquals(140.0, fareLimitedAmount["2021-28"])
        Assertions.assertEquals(500.0, fareLimitedAmount["2021-29"])
    }
}