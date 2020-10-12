package com.borkness.f1api.models

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Roles(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val name: String,
        val description: String
)