package com.borkness.f1api

import com.borkness.f1api.models.Constructors
import com.borkness.f1api.repository.ConstructorsRepository
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
class ConstructorsTest {

    @MockBean
    private lateinit var constructorsRepository: ConstructorsRepository

    @Test
    fun `can get all constructors`() {
        val carConstructor1 = Constructors(1, "mercedes", "Mercedes", "German", "http://localhost")
        val carConstructor2 = Constructors(2, "mclaren", "McLaren", "British", "http://localhost")
        val carConstructor3 = Constructors(3, "williams", "Williams", "British", "http://localhost")

        Mockito.`when`(constructorsRepository.findAll()).thenReturn(listOf(carConstructor1, carConstructor2, carConstructor3))

        val constructorsResult : List<Constructors> = constructorsRepository.findAll()

        Assertions.assertEquals(3, constructorsResult.size)
        Assertions.assertEquals("German", constructorsResult[0].nationality)
        Assertions.assertEquals("Williams", constructorsResult[2].name)
    }

    @Test
    fun `can get an individual constructor`() {
        val carConstructor = Constructors(1, "mercedes", "Mercedes", "German", "http://localhost")

        Mockito.`when`(constructorsRepository.findById(1)).thenReturn(Optional.of(carConstructor))

        val constructorResult : Optional<Constructors> = constructorsRepository.findById(1)

        Assertions.assertEquals(Optional.of("mercedes"), constructorResult.map { it.ref })
    }
}