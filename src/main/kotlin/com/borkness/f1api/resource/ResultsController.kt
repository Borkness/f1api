package com.borkness.f1api.resource

import com.borkness.f1api.models.Results
import com.borkness.f1api.repository.ResultsRepository
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/results")
class ResultsController(private val resultsRepository: ResultsRepository) {

    @GetMapping
    fun getAllResults() : ResponseEntity<List<Results>> {
        val results : List<Results> = resultsRepository.findAll().map { results ->
            results.add(linkTo(methodOn(this.javaClass).getAllResults()).withSelfRel())
        }
        return ResponseEntity.ok(results)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") resultId: Long) : ResponseEntity<Results> {
        return resultsRepository.findById(resultId).map { results ->
            results.add(linkTo(methodOn(this.javaClass).getAllResults()).withRel("results"))
            results.add(linkTo(methodOn(DriversController::class.java).getDriverById(results.driver.id)).withRel("drivers"))
            results.add(linkTo(methodOn(this.javaClass).getById(resultId)).withSelfRel())
            ResponseEntity.ok(results)
        }.orElse(ResponseEntity.notFound().build())
    }
}