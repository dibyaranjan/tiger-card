package com.tigercard.farelimit.rule

import com.tigercard.entity.Journey
import com.tigercard.farelimit.FareLimitService

class DailyFareLimitRule(
    private val fareLimitService: FareLimitService,
    override val nextRule: FareLimitRule?,
) : FareLimitRule {
    override fun applyRule(
        journeyList: List<Journey>,
        amount: Map<String, Double>
    ): Map<String, Double> {
        val fareLimitByDay = fareLimitService.getFareLimitByDay(journeyList)
        val dailyFareLimited = amount.map { (date, totalCost) ->
            val fareLimit = fareLimitByDay[date] ?: error("Fare limit not defined for $date")

            if (totalCost >= fareLimit) {
                date to fareLimit
            } else {
                date to totalCost
            }
        }.toMap()

        return nextRule?.applyRule(journeyList, dailyFareLimited) ?: dailyFareLimited
    }
}