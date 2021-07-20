package com.tigercard.farelimit.dao

import com.tigercard.entity.Route

abstract class AbstractFareLimitDao : FareLimitDao {
    protected abstract val fareLimitRules: Map<Route, Double>

    override fun getFareLimit(route: Route) = fareLimitRules[route] ?: 0.0
}