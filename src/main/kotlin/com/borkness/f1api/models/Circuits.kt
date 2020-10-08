package com.borkness.f1api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.hateoas.RepresentationModel
import javax.persistence.*

@Entity
@Table(name = "circuits")
data class Circuits (
    @Id
    @Column(name = "circuitid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    @JsonIgnore
    @Column(name = "circuitref")
    val circuitRef : String,
    val name : String,
    val location : String? = null,
    val country : String? = null,
    @Column(name = "lat")
    val latitude : Float? = null,
    @Column(name = "lng")
    val longitude : Float? = null,
    val alt : Int? = null,
    val url : String
) : RepresentationModel<Circuits>()