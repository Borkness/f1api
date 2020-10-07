package com.borkness.f1api.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "status")
data class Statuses(
        @Id
        @Column(name = "statusid")
        val id: Long,
        val status: String
)