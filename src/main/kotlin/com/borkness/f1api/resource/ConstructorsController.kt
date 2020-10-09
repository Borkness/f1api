package com.borkness.f1api.resource

import com.borkness.f1api.models.Constructors
import com.borkness.f1api.repository.ConstructorsRepository
import com.borkness.f1api.utilities.DataWrapper
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/constructors")
class ConstructorsController (private val constructorsRepository: ConstructorsRepository){

    /**
     * Get all constructors
     *
     * Get a list of all constructors
     */
    @GetMapping
    fun getAll() : ResponseEntity<List<Constructors>> {
        val constructorResponse = constructorsRepository.findAll().map { constructors ->
            constructors.add(linkTo(methodOn(this.javaClass).getById(constructors.id)).withSelfRel())
        }
        return ResponseEntity.ok(constructorResponse)
    }

    /**
     * Get Constructor by Id
     *
     * Get representation of an individual constructor based on passed Id
     *
     * @param constructorId Id of the constructor in database
     */
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") constructorId : Long) : ResponseEntity<DataWrapper<Constructors>> {
        return constructorsRepository.findById(constructorId).map { constructor ->
            constructor.add(linkTo(methodOn(this.javaClass).getById(constructorId)).withSelfRel())
            constructor.add(linkTo(methodOn(this.javaClass).getAll()).withRel("constructors"))
            val constructorWrapper : DataWrapper<Constructors> = DataWrapper(constructor)
            ResponseEntity.ok(constructorWrapper)
        }.orElse(ResponseEntity.notFound().build())
    }
}