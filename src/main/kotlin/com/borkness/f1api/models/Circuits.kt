package com.borkness.f1api.models

import javax.persistence.*

@Entity
@Table(name = "circuits")
data class Circuits (
    @Id
    @Column(name = "circuitid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    @Column(name = "circuitRef")
    val circuitRef : String,
    val name : String,
    val location : String? = null,
    val country : String? = null,
    val latitude : Float? = null,
    val longitude : Float? = null,
    val alt : Int,
    val url : String
)