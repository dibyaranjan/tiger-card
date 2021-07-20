package com.tigercard.farelimit.dao

import com.tigercard.entity.Route

interface FareLimitDao {
    fun getFareLimit(route: Route): Double
}