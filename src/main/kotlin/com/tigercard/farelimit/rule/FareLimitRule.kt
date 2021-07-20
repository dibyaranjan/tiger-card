package com.tigercard.farelimit.rule

import com.tigercard.entity.Journey

interface FareLimitRule {
    val nextRule: FareLimitRule?

    fun applyRule(
        journeyList: List<Journey>,
        amount: Map<String, Double>
    ): Map<String, Double>
}