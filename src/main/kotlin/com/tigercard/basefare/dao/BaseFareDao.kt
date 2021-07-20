package com.tigercard.basefare.dao

import com.tigercard.entity.Route

interface BaseFareDao {
    fun getFare(route: Route): Double
}