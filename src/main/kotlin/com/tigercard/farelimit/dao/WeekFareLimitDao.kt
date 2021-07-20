package com.tigercard.farelimit.dao

import com.tigercard.entity.Zone
import com.tigercard.mockdb.FareLimitRules

class WeekFareLimitDao : AbstractFareLimitDao() {
    override val fareLimitRules = FareLimitRules.weekLimit
}