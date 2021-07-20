package com.tigercard.mockdb

import com.tigercard.surcharge.SurchargeType

object SurchargeRules {
    val rules = mapOf(
        SurchargeType.WEEKDAY_PEAK to 5.0,
        SurchargeType.WEEKEND_PEAK to 5.0,
        SurchargeType.NON_PEAK to 0.0
    )
}