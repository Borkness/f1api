package com.borkness.f1api.resource

import com.borkness.f1api.models.Circuits
import com.borkness.f1api.repository.CircuitsRepository
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

    @GetMapping
    fun getAll() : ResponseEntity<List<Circuits>> {
        val circuits = circuitsRepository.findAll().map { circuits ->
            circuits.add(linkTo(methodOn(javaClass).getById(circuits.id)).withSelfRel())
        }

        return ResponseEntity.ok(circuits)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") circuitId : Long) : ResponseEntity<Circuits> {
        return circuitsRepository.findById(circuitId).map { circuit ->
            circuit.add(linkTo(methodOn(javaClass).getById(circuit.id)).withSelfRel())
            ResponseEntity.ok(circuit)
        }.orElse(ResponseEntity.notFound().build())
    }
}