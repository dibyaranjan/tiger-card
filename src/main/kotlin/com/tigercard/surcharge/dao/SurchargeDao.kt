package com.tigercard.surcharge.dao

import com.tigercard.surcharge.SurchargeType

interface SurchargeDao {
    fun getSurcharge(surchargeType: SurchargeType): Double
}