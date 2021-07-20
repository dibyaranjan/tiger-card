package com.tigercard.basefare.dao

import com.tigercard.entity.Route
import com.tigercard.entity.Zone
import com.tigercard.mockdb.ZoneRules

class InMemoryBaseFareDao : BaseFareDao {
    override fun getFare(
        route: Route
    ): Double = ZoneRules.rules[route] ?: error("Unknown zones for $route")
}