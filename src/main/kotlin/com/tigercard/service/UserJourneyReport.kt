package com.tigercard.service

import com.tigercard.basefare.BaseFareService
import com.tigercard.entity.Journey
import com.tigercard.entity.Route
import com.tigercard.farelimit.rule.FareLimitRule
import com.tigercard.surcharge.SurchargeService
import java.time.format.DateTimeFormatter

class UserJourneyReport(
    private val baseFareService: BaseFareService,
    private val surchargeService: SurchargeService,
    private val fareLimitRule: FareLimitRule
) {
    fun apply(journeyList: List<Journey>): Double {
        val journeyGroupedByDate = journeyList.groupBy {
            it.journeyTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        }

        val totalAmountByDate = journeyGroupedByDate.map { (k, v) ->
            k to v.map {
                val baseFare = baseFareService.getFare(Route(it.fromZone, it.toZone))

                val surcharge = surchargeService.getSurcharge(it.journeyTime)

                baseFare + surcharge
            }.sum()
        }.toMap()

        val fareLimitedCost = fareLimitRule.applyRule(journeyList, totalAmountByDate)

        return fareLimitedCost.values.sum()
    }
}