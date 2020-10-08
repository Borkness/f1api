package com.borkness.f1api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "constructors")
data class Constructors (
        @Id
        @Column(name = "constructorid")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,
        @JsonIgnore
        @Column(name = "constructorref")
        val ref : String,
        val name : String,
        val nationality : String? = null,
        @JsonIgnore
        val url : String
)