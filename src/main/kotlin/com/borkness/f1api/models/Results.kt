package com.borkness.f1api.models

import javax.persistence.*

@Entity
@Table(name = "results")
data class Results (
    @Id
    @Column(name = "resultid")
    val id : Long,
    @Column(name = "raceid")
    val raceId : Int,
    @OneToOne
    @JoinColumn(name = "driverid")
    val driver : Drivers,
    @Column(name = "constructorid")
    val constructorId : Int,
    val number : Int? = null,
    val grid : Int,
    val position : Int? = null,
    @Column(name = "positiontext")
    val positionText : String,
    @Column(name = "positionorder")
    val positionOrder : Int,
    val points : Float,
    val laps : Int,
    val time : String? = null,
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