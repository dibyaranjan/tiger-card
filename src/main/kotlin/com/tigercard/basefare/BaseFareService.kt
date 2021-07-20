package com.tigercard.basefare

import com.tigercard.basefare.dao.BaseFareDao
import com.tigercard.entity.Route

class BaseFareService(
    private val baseFareDao: BaseFareDao
) {
    fun getFare(route: Route): Double = baseFareDao.getFare(route)
}