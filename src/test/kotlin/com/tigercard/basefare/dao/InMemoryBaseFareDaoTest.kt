package com.tigercard.basefare.dao

import com.tigercard.entity.Route
import com.tigercard.entity.Zone
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

internal class InMemoryBaseFareDaoTest {
    private lateinit var baseFareDao: InMemoryBaseFareDao

    @BeforeEach
    fun setup() {
        baseFareDao = InMemoryBaseFareDao()
    }

    @Test
    fun `when journey is from zone 1 to zone 1 then the fare should be 25`() {
        val fare = baseFareDao.getFare(Route(Zone.ZONE_ONE, Zone.ZONE_ONE))

        Assertions.assertEquals(25.0, fare)
    }

    @Test
    fun `when journey is from zone 1 to zone 2 then the fare should be 30`() {
        val fare = baseFareDao.getFare(Route(Zone.ZONE_ONE, Zone.ZONE_TWO))

        Assertions.assertEquals(30.0, fare)
    }

    @Test
    fun `when journey is from zone 2 to zone 2 then the fare should be 20`() {
        val fare = baseFareDao.getFare(Route(Zone.ZONE_TWO, Zone.ZONE_TWO))

        Assertions.assertEquals(20.0, fare)
    }

    @Test
    fun `when zoneId is not known then an exception should be thrown`() {
        Assertions.assertThrows(IllegalStateException::class.java) { baseFareDao.getFare(Route(Zone(3), Zone(1))) }
    }
}