package com.borkness.f1api

import com.borkness.f1api.models.*
import com.borkness.f1api.repository.ResultsRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import java.sql.Time
import java.util.*

@AutoConfigureMockRestServiceServer
@SpringBootTest
@ActiveProfiles("test")
class ResultsTest {

    @MockBean
    private lateinit var resultsRepository : ResultsRepository

    @Test
    fun `can get a list of results`(){
        val hamilton = Drivers(1, "ham", 44, "HAM", "Lewis", "Hamilton", "01/07/80", "British", "http://localhost")
        val bottas = Drivers(2, "bot", 77, "BOT", "Valtteri", "Bottas", "01/07/88", "Finnish", "http://localhost")
        val race = Races(1, 2020, 3, 1, "Silverstone", Date(100000), Time(1130), "http://localhost")
        val constructor = Constructors(1, "mercedes", "Mercedes", "German", "http://localhost")
        val status = Statuses(1, "Finished")
        val result1 = Results(1, race, hamilton, constructor, 44, 1, 1, "1", 1, 25f, 70, status = status)
        val result2 = Results(2, race, bottas, constructor, 77, 2, 2, "2", 2, 18f, 70, status = status)

        Mockito.`when`(resultsRepository.findAll()).thenReturn(listOf(result1, result2))

        val driverResultsList : List<Results> = resultsRepository.findAll()

        Assertions.assertEquals(2, driverResultsList.size)
        Assertions.assertEquals("Lewis", driverResultsList[0].driver.forename)
        Assertions.assertEquals("Valtteri", driverResultsList[1].driver.forename)
    }

    @Test
    fun `can get an individual result`(){
        val hamilton = Drivers(1, "ham", 44, "HAM", "Lewis", "Hamilton", "01/07/80", "British", "http://localhost")
        val race = Races(1, 2020, 3, 1, "Silverstone", Date(100000), Time(1130), "http://localhost")
        val constructor = Constructors(1, "mercedes", "Mercedes", "German", "http://localhost")
        val status = Statuses(1, "Finished")
        val result1 = Results(1, race, hamilton, constructor, 44, 1, 1, "1", 1, 25f, 70, status = status)

        Mockito.`when`(resultsRepository.findById(1)).thenReturn(Optional.of(result1))

        val driverResult : Optional<Results> = resultsRepository.findById(1)

        Assertions.assertEquals(Optional.of("Lewis"), driverResult.map { it.driver.forename })
    }
}