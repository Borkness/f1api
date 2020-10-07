package com.borkness.f1api.resource

import com.borkness.f1api.models.Results
import com.borkness.f1api.repository.ResultsRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.function.ServerResponse.ok

@RestController
@RequestMapping("api/v1/results")
class ResultsController(private val resultsRepository: ResultsRepository) {

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") resultId : Long) : ResponseEntity<Results> {
        return resultsRepository.findById(resultId).map { results ->
            ResponseEntity.ok(results)
        }.orElse(ResponseEntity.notFound().build())
    }
}