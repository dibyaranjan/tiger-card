package com.tigercard.farelimit.dao

import com.tigercard.mockdb.FareLimitRules

class DayFareLimitDao : AbstractFareLimitDao() {
    override val fareLimitRules = FareLimitRules.dayLimit
}