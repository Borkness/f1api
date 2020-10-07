package com.borkness.f1api.models

import java.sql.Time
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "races")
data class Races (
        @Id
        @Column(name = "raceid")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,
        val year : Long,
        val round : Int,
        @Column(name = "circuitid")
        val circuitId : Int,
        val name : String,
        val date: Date,
        val time: Time? = null,
        val url : String? = null
)