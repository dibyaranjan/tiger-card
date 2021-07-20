package com.tigercard.farelimit.dao

import com.tigercard.farelimit.FareLimitType

class FareLimitDaoFactory {
    fun getFareLimitByType(type: FareLimitType): FareLimitDao {
        return when (type) {
            FareLimitType.DAY -> DayFareLimitDao()
            FareLimitType.WEEK -> WeekFareLimitDao()
            else -> error("Not implemented fare cap for $type")
        }
    }
}