package com.borkness.f1api.resource

import com.borkness.f1api.utilities.DataWrapper
import com.borkness.f1api.models.Results
import com.borkness.f1api.repository.ResultsRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/results")
class ResultsController(private val resultsRepository: ResultsRepository) {

    /**
     * Get all Results
     *
     * Return a paginated set of all results
     *
     * @param page Requested page default is 0
     * @param pageSize Page size default is 25
     */
    @GetMapping
    fun getAllResults(@RequestParam(defaultValue = "0") page : Int = 0,
                      @RequestParam(defaultValue = "25") pageSize : Int = 25) : ResponseEntity<Page<Results>> {
        val pagination : PageRequest = PageRequest.of(page, pageSize)

        val results : Page<Results> = resultsRepository.findAll(pagination).map { results ->
            results.add(linkTo(methodOn(this.javaClass).getById(results.id)).withSelfRel())
        }
        return ResponseEntity.ok(results)
    }

    /**
     * Get Results By Id
     *
     * Return a resource of results based on the passed Id
     *
     * @param resultId Result Id in results table
     */
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") resultId: Long) : ResponseEntity<DataWrapper<Results>> {
        return resultsRepository.findById(resultId).map { results ->
            results.add(linkTo(methodOn(this.javaClass).getAllResults()).withRel("results"))
            results.add(linkTo(methodOn(DriversController::class.java).getDriverById(results.driver.id)).withRel("drivers"))
            results.add(linkTo(methodOn(ConstructorsController::class.java).getById(results.constructor.id)).withRel("constructors"))
            results.add(linkTo(methodOn(this.javaClass).getById(resultId)).withSelfRel())
            val dataWrapper : DataWrapper<Results> = DataWrapper(results)
            ResponseEntity.ok(dataWrapper)
        }.orElse(ResponseEntity.notFound().build())
    }
}