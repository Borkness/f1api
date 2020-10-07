package com.borkness.f1api.models

import javax.persistence.*

@Entity
@Table(name = "constructors")
data class Constructors (
        @Id
        @Column(name = "constructorid")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,
        @Column(name = "constructorref")
        val ref : String,
        val name : String,
        val nationality : String? = null,
        val url : String
)