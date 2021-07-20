package com.tigercard.surcharge.dao

import com.tigercard.mockdb.SurchargeRules
import com.tigercard.surcharge.SurchargeType

class InMemorySurchargeDao : SurchargeDao {
    override fun getSurcharge(
        surchargeType: SurchargeType
    ): Double = SurchargeRules.rules[surchargeType] ?: error("Surcharge not configured for $surchargeType")
}