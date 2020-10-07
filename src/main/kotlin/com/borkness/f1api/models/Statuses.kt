package com.borkness.f1api.models

import javax.persistence.*

@Entity
@Table(name = "status")
data class Statuses(
        @Id
        @Column(name = "statusid")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val status: String
)