package com.tigercard.mockdb

import com.tigercard.entity.Route
import com.tigercard.entity.Zone

object FareLimitRules {
    /**
     * Ideally should be in a db
     */
    val dayLimit = mapOf(
        Route(Zone.ZONE_ONE, Zone.ZONE_ONE) to 100.0,
        Route(Zone.ZONE_ONE, Zone.ZONE_TWO) to 120.0,
        Route(Zone.ZONE_TWO, Zone.ZONE_ONE) to 120.0,
        Route(Zone.ZONE_TWO, Zone.ZONE_TWO) to 80.0,
    )

    val weekLimit = mapOf(
        Route(Zone.ZONE_ONE, Zone.ZONE_ONE) to 500.0,
        Route(Zone.ZONE_ONE, Zone.ZONE_TWO) to 600.0,
        Route(Zone.ZONE_TWO, Zone.ZONE_ONE) to 600.0,
        Route(Zone.ZONE_TWO, Zone.ZONE_TWO) to 400.0
    )
}