package com.tigercard

import com.tigercard.entity.Journey
import com.tigercard.entity.Zone
import com.tigercard.service.createJourneyTime

class JourneyListTestData {
    companion object {
        val ONE_DAY_TRIP_OVER_DAY_LIMIT = listOf(
            Journey(createJourneyTime("2021-07-12T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-12T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-12T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-12T08:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
        )

        val TWO_DAY_TRIP_OVER_DAY_LIMIT = listOf(
            Journey(createJourneyTime("2021-07-12T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-12T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-12T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-12T08:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-13T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-13T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-13T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-13T08:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
        )

        val ONE_WEEK_TRIP_UNDER_DAY_LIMIT_OVER_WEEK_LIMIT = listOf(
            Journey(createJourneyTime("2021-07-12T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-12T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-12T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-13T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-13T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-13T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-14T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-14T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-14T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-15T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-15T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-15T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-16T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-16T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-16T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-17T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-17T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-17T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-18T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-18T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-18T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
        )

        val TWO_DAY_TRIP_MULTI_ZONE_UNDER_LIMIT = listOf(
            Journey(createJourneyTime("2021-07-12T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-13T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_TWO),
        )

        val TWO_WEEK_TRIP_UNDER_DAY_LIMIT_UNDER_WEEK_LIMIT_MULTI_ZONE = listOf(
            Journey(createJourneyTime("2021-07-17T07:00:00"), Zone.ZONE_TWO, Zone.ZONE_TWO),
            Journey(createJourneyTime("2021-07-17T07:30:00"), Zone.ZONE_TWO, Zone.ZONE_TWO),
            Journey(createJourneyTime("2021-07-17T08:00:00"), Zone.ZONE_TWO, Zone.ZONE_TWO),

            Journey(createJourneyTime("2021-07-18T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-18T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-18T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-19T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-19T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_TWO),
            Journey(createJourneyTime("2021-07-19T08:00:00"), Zone.ZONE_TWO, Zone.ZONE_ONE),
        )

        val TWO_WEEK_TRIP_UNDER_DAY_LIMIT_OVER_WEEK_LIMIT_MULTI_ZONE = listOf(
            Journey(createJourneyTime("2021-07-17T07:00:00"), Zone.ZONE_TWO, Zone.ZONE_TWO),
            Journey(createJourneyTime("2021-07-17T07:30:00"), Zone.ZONE_TWO, Zone.ZONE_TWO),
            Journey(createJourneyTime("2021-07-17T08:00:00"), Zone.ZONE_TWO, Zone.ZONE_TWO),

            Journey(createJourneyTime("2021-07-18T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-18T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_TWO),
            Journey(createJourneyTime("2021-07-18T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-19T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-19T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-19T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-20T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-20T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-20T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-21T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-21T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-21T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-22T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-22T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-22T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-23T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-23T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-23T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-24T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-24T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-24T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),

            Journey(createJourneyTime("2021-07-25T07:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-25T07:30:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
            Journey(createJourneyTime("2021-07-25T08:00:00"), Zone.ZONE_ONE, Zone.ZONE_ONE),
        )
    }
}