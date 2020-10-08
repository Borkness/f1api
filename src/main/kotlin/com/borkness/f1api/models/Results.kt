package com.borkness.f1api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "results")
data class Results (
    @Id
    @Column(name = "resultid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    @OneToOne
    @JoinColumn(name = "raceid")
    @JsonIgnoreProperties("circuitId")
    val race : Races,
    @OneToOne
    @JoinColumn(name = "driverid")
    val driver : Drivers,
    @OneToOne
    @JoinColumn(name = "constructorid")
    val constructor : Constructors,
    val number : Int? = null,
    @JsonProperty("startingPosition")
    val grid : Int,
    @JsonProperty("finishingPosition")
    val position : Int? = null,
    @JsonIgnore
    @Column(name = "positiontext")
    val positionText : String,
    @JsonIgnore
    @Column(name = "positionorder")
    val positionOrder : Int,
    val points : Float,
    val laps : Int,
    val time : String? = null,
    @JsonIgnore
    val milliseconds : Int? = null,
    @Column(name = "fastestlap")
    val fastestLap : Int? = null,
    val rank : Int,
    @Column(name = "fastestlaptime")
    val fastestLapTime : String? = null,
    @Column(name = "fastestlapspeed")
    val fastestLapSpeed : String? = null,
    @OneToOne
    @JoinColumn(name = "statusid")
    val status : Statuses
)