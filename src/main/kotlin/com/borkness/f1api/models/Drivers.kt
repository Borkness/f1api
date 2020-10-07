package com.borkness.f1api.models

import javax.persistence.*

@Entity
@Table(name = "drivers")
data class Drivers(
        @Id
        @Column(name = "driverid")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column(name = "driverref")
        val ref: String,
        val number: Int? = null,
        val code: String? = null,
        val forename: String,
        val surname: String,
        val dob: String? = null,
        val nationality: String? = null,
        val url: String
)