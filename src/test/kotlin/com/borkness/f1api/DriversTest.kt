package com.borkness.f1api

import com.borkness.f1api.models.Drivers
import com.borkness.f1api.repository.DriversRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@AutoConfigureMockRestServiceServer
@SpringBootTest
class DriversTest {

    @MockBean
    private lateinit var driversRepository: DriversRepository

    @Test
    fun `can get a list of drivers`() {
        val hamilton = Drivers(1, "ham", 44, "HAM", "Lewis", "Hamilton", "01/07/80", "British", "http://localhost")
        val bottas = Drivers(2, "bot", 77, "BOT", "Valtteri", "Bottas", "01/07/88", "Finnish", "http://localhost")
        val alonso = Drivers(3, "al0", 14, "ALO", "Fernando", "Alonso", "01/01/1970", "Spanish", "http://localhost")

        Mockito.`when`(driversRepository.findAll()).thenReturn(listOf(hamilton, bottas, alonso))

        val driversResult : List<Drivers> = driversRepository.findAll()

        Assertions.assertEquals(3, driversResult.size)
        Assertions.assertEquals("Alonso", driversResult[2].surname)
        Assertions.assertEquals("Hamilton", driversResult[0].surname)
    }

    @Test
    fun `get an individual driver`() {
        val alonso = Drivers(3, "al0", 14, "ALO", "Fernando", "Alonso", "01/01/1970", "Spanish", "http://localhost")

        Mockito.`when`(driversRepository.findById(3)).thenReturn(Optional.of(alonso))

        val getDriverResult : Optional<Drivers> = driversRepository.findById(3)

        Assertions.assertEquals(Optional.of("Alonso"), getDriverResult.map { it.surname })
    }
}