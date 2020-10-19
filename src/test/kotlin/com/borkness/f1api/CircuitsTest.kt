package com.borkness.f1api

import com.borkness.f1api.models.Circuits
import com.borkness.f1api.repository.CircuitsRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import java.util.*

@AutoConfigureMockRestServiceServer
@SpringBootTest
@ActiveProfiles("test")
class CircuitsTest {

    @MockBean
    private lateinit var circuitsRepository: CircuitsRepository

    @Test
    fun `can get a list of all circuits`() {
        val circuit1 = Circuits(1, "aus", "Albert Park", "Australia", "Australia", 11.22f, 13.37f, 0, "http://localhost")
        val circuit2 = Circuits(2, "soc", "Sochi Auto", "Sochi", "Russia", 12.32f, 18.97f, 0, "http://localhost")
        val circuit3 = Circuits(3, "sil", "Silverstone", "Silverstone", "United Kingdom", 22.22f, 87.37f, 0, "http://localhost")

        Mockito.`when`(circuitsRepository.findAll()).thenReturn(listOf(circuit1, circuit2, circuit3))

        val circuitList : List<Circuits> = circuitsRepository.findAll()

        Assertions.assertEquals(3, circuitList.size)
        Assertions.assertEquals("Australia", circuitList[0].country)
        Assertions.assertEquals("United Kingdom", circuitList[2].country)
    }

    @Test
    fun `can get a single circuit`() {
        val circuit = Circuits(1, "aus", "Albert Park", "Australia", "Australia", 11.22f, 13.37f, 0, "http://localhost")

        Mockito.`when`(circuitsRepository.findById(1)).thenReturn(Optional.of(circuit))

        val australia : Optional<Circuits> = circuitsRepository.findById(1)

        Assertions.assertEquals(Optional.of("Albert Park"), australia.map { it.name })
    }
}