package com.tigercard.mockdb

import com.tigercard.entity.Route
import com.tigercard.entity.Zone

object ZoneRules {
    /**
     * Ideally should be in a db
     */
    val rules = mapOf(
        Route(Zone.ZONE_ONE, Zone.ZONE_ONE) to 25.0,
        Route(Zone.ZONE_ONE, Zone.ZONE_TWO) to 30.0,
        Route(Zone.ZONE_TWO, Zone.ZONE_ONE) to 30.0,
        Route(Zone.ZONE_TWO, Zone.ZONE_TWO) to 20.0,
    )
}