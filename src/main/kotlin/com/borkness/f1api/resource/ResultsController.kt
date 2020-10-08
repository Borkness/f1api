package com.borkness.f1api.resource

import com.borkness.f1api.models.Results
import com.borkness.f1api.repository.ResultsRepository
import com.borkness.f1api.transformers.ConstructorsTransformer
import com.borkness.f1api.transformers.DriversTransformer
import com.borkness.f1api.transformers.RacesTransformer
import com.borkness.f1api.transformers.ResultsTransformer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/results")
class ResultsController(private val resultsRepository: ResultsRepository) {

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") resultId : Long) : ResponseEntity<Results> {
        return resultsRepository.findById(resultId).map { results ->
//            val resultsTransformer = ResultsTransformer(
//                    results.id,
//                    RacesTransformer(
//                            results.race.id,
//                            results.race.name,
//                            results.race.date,
//                            results.race.time
//                    ),
//                    DriversTransformer(
//                            results.driver.id,
//                            results.driver.forename,
//                            results.driver.surname,
//                            results.driver.number,
//                            results.driver.code,
//                            results.driver.dob,
//                            results.driver.nationality
//                    ),
//                    ConstructorsTransformer(
//                            results.constructor.id,
//                            results.constructor.name,
//                            results.constructor.nationality
//                    ),
//                    results.number,
//                    results.grid,
//                    results.position
//            )
            ResponseEntity.ok(results)
        }.orElse(ResponseEntity.notFound().build())
    }
}