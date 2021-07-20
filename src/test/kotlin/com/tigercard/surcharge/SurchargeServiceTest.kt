package com.tigercard.surcharge

import com.tigercard.surcharge.dao.SurchargeDao
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class SurchargeServiceTest {

    lateinit var surchargeService: SurchargeService
    lateinit var surchargeDao: SurchargeDao

    @BeforeEach
    fun setUp() {
        surchargeDao = mockk()

        surchargeService = SurchargeService(surchargeDao)
    }

    @Test
    fun `when journey is non peak time of weekday then surcharge should be 0`() {
        val journeyDate = LocalDateTime.parse("2021-07-12T10:31:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        every { surchargeDao.getSurcharge(SurchargeType.NON_PEAK) } returns 0.0

        val surcharge = surchargeService.getSurcharge(journeyDate)

        Assertions.assertEquals(0.0, surcharge)
        verify { surchargeDao.getSurcharge(SurchargeType.NON_PEAK) }
    }

    @Test
    fun `when journey is in non peak time of weekend then surcharge should be 0`() {
        val journeyDate = LocalDateTime.parse("2021-07-10T23:10:10", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        every { surchargeDao.getSurcharge(SurchargeType.NON_PEAK) } returns 0.0

        val surcharge = surchargeService.getSurcharge(journeyDate)

        Assertions.assertEquals(0.0, surcharge)
        verify { surchargeDao.getSurcharge(SurchargeType.NON_PEAK) }
    }

    @Test
    fun `when journey is in morning peak time of weekday then surcharge should be 5`() {
        val journeyDate = LocalDateTime.parse("2021-07-12T10:29:10", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        every { surchargeDao.getSurcharge(SurchargeType.WEEKDAY_PEAK) } returns 5.0

        val surcharge = surchargeService.getSurcharge(journeyDate)

        Assertions.assertEquals(5.0, surcharge)
        verify { surchargeDao.getSurcharge(SurchargeType.WEEKDAY_PEAK) }
    }

    @Test
    fun `when journey is in evening peak time of weekday then surcharge should be 5`() {
        val journeyDate = LocalDateTime.parse("2021-07-12T19:10:10", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        every { surchargeDao.getSurcharge(SurchargeType.WEEKDAY_PEAK) } returns 5.0

        val surcharge = surchargeService.getSurcharge(journeyDate)

        Assertions.assertEquals(5.0, surcharge)
        verify { surchargeDao.getSurcharge(SurchargeType.WEEKDAY_PEAK) }
    }

    @Test
    fun `when journey is in morning peak time of weekend then surcharge should be 5`() {
        val journeyDate = LocalDateTime.parse("2021-07-10T09:10:10", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        every { surchargeDao.getSurcharge(SurchargeType.WEEKEND_PEAK) } returns 5.0

        val surcharge = surchargeService.getSurcharge(journeyDate)

        Assertions.assertEquals(5.0, surcharge)
        verify { surchargeDao.getSurcharge(SurchargeType.WEEKEND_PEAK) }
    }

    @Test
    fun `when journey is in evening peak time of weekend then surcharge should be 5`() {
        val journeyDate = LocalDateTime.parse("2021-07-10T18:10:10", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        every { surchargeDao.getSurcharge(SurchargeType.WEEKEND_PEAK) } returns 5.0

        val surcharge = surchargeService.getSurcharge(journeyDate)

        Assertions.assertEquals(5.0, surcharge)
        verify { surchargeDao.getSurcharge(SurchargeType.WEEKEND_PEAK) }
    }
}