package com.tigercard.config

import com.tigercard.basefare.BaseFareService
import com.tigercard.basefare.dao.InMemoryBaseFareDao
import com.tigercard.farelimit.*
import com.tigercard.farelimit.dao.FareLimitDaoFactory
import com.tigercard.farelimit.rule.DailyFareLimitRule
import com.tigercard.farelimit.rule.FareLimitRule
import com.tigercard.farelimit.rule.WeeklyFareLimitRule
import com.tigercard.service.UserJourneyReport
import com.tigercard.surcharge.SurchargeService
import com.tigercard.surcharge.dao.InMemorySurchargeDao

class UserJourneyReportFactory {
    companion object {
        fun getInstance(): UserJourneyReport {
            val baseFareService = createBaseFareService()
            val surchargeService = createSurchargeService()
            val fareLimitRules = createFareLimitRules()
            return UserJourneyReport(baseFareService, surchargeService, fareLimitRules)
        }

        private fun createBaseFareService(): BaseFareService {
            val baseFareDao = InMemoryBaseFareDao()

            return BaseFareService(baseFareDao)
        }

        private fun createSurchargeService(): SurchargeService {
            val surchargeDao = InMemorySurchargeDao()

            return SurchargeService(surchargeDao)
        }

        private fun createFareLimitService(): FareLimitService {
            val fareLimitFactory = FareLimitDaoFactory()

            return FareLimitService(fareLimitFactory)
        }

        private fun createFareLimitRules(): FareLimitRule {
            val fareLimitService = createFareLimitService()
            return DailyFareLimitRule(fareLimitService, WeeklyFareLimitRule(fareLimitService, null))
        }
    }
}