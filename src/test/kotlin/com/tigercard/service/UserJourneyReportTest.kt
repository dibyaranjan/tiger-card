package com.tigercard.service

import com.tigercard.JourneyListTestData
import com.tigercard.config.UserJourneyReportFactory
import com.tigercard.service.UserJourneyReport
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class UserJourneyReportTest {
    private lateinit var userJourneyReport: UserJourneyReport

    @BeforeEach
    fun setUp() {
        userJourneyReport = UserJourneyReportFactory.getInstance()
    }

    @Test
    fun `when daily limit has reached then discount should be applied`() {
        val amount = userJourneyReport.apply(JourneyListTestData.TWO_DAY_TRIP_OVER_DAY_LIMIT)

        Assertions.assertEquals(200.0, amount)
    }

    @Test
    fun `when weekly limit reached without daily limit reach then weekly discount should be applied`() {
        val amount = userJourneyReport.apply(JourneyListTestData.ONE_WEEK_TRIP_UNDER_DAY_LIMIT_OVER_WEEK_LIMIT)

        Assertions.assertEquals(500.0, amount)
    }

    @Test
    fun `when day limit and weekly limit is not reached then actual fare should be charged`() {
        val amount =
            userJourneyReport.apply(JourneyListTestData.TWO_WEEK_TRIP_UNDER_DAY_LIMIT_UNDER_WEEK_LIMIT_MULTI_ZONE)

        Assertions.assertEquals(235.0, amount)
    }

    @Test
    fun `when journey is spread across weeks then rules should be applied to each week`() {
        val amount =
            userJourneyReport.apply(JourneyListTestData.TWO_WEEK_TRIP_UNDER_DAY_LIMIT_OVER_WEEK_LIMIT_MULTI_ZONE)

        Assertions.assertEquals(640.0, amount)
    }
}