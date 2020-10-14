package com.borkness.f1api.resource

import com.borkness.f1api.models.Circuits
import com.borkness.f1api.repository.CircuitsRepository
import com.borkness.f1api.utilities.DataWrapper
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/circuits")
class CircuitsController(private val circuitsRepository: CircuitsRepository) {

    /**
     * Get all circuits
     *
     * Get a resource of all circuits
     */
    @GetMapping
    fun getAll() : ResponseEntity<DataWrapper<List<Circuits>>> {
        val circuits = circuitsRepository.findAll().map { circuits ->
            circuits.add(linkTo(methodOn(javaClass).getById(circuits.id)).withSelfRel())
        }

        val circuitsWrapper : DataWrapper<List<Circuits>> = DataWrapper(circuits)

        return ResponseEntity.ok(circuitsWrapper)
    }

    /**
     * Get circuit by Id
     *
     * Get a resource of a specific circuit based on id
     *
     * @param circuitId Circuit Id in Database
     */
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") circuitId : Long) : ResponseEntity<DataWrapper<Circuits>> {
        return circuitsRepository.findById(circuitId).map { circuit ->
            circuit.add(linkTo(methodOn(javaClass).getById(circuit.id)).withSelfRel())
            val circuitWrapper : DataWrapper<Circuits> = DataWrapper(circuit)
            ResponseEntity.ok(circuitWrapper)
        }.orElse(ResponseEntity.notFound().build())
    }
}