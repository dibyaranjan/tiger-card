package com.tigercard.farelimit.rule

import com.tigercard.entity.Journey
import com.tigercard.farelimit.FareLimitService
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields

class WeeklyFareLimitRule(
    private val fareLimitService: FareLimitService,
    override val nextRule: FareLimitRule?
) : FareLimitRule {
    override fun applyRule(
        journeyList: List<Journey>,
        amount: Map<String, Double>
    ): Map<String, Double> {
        val dateGroupedByWeek = amount.keys.groupBy {
            val date = LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val weekNumber = date.get(WeekFields.ISO.weekOfYear())

            "${date.year}-$weekNumber"
        }

        val totalAmountByWeek = dateGroupedByWeek.map { (k, v) ->
            k to v.map {
                amount[it]!!
            }.sum()
        }.toMap()

        val fareLimitByWeek = fareLimitService.getFareLimitByWeek(journeyList)

        val amountForWeek = totalAmountByWeek.map { (date, totalCost) ->
            val fareLimit = fareLimitByWeek[date] ?: error("Fare limit not found for $date")

            if (totalCost > fareLimit) {
                date to fareLimit
            } else {
                date to totalCost
            }
        }.toMap()

        return nextRule?.applyRule(journeyList, amountForWeek) ?: amountForWeek
    }
}