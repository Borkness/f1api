package com.borkness.f1api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import javax.persistence.*

@Entity
@Table(name = "drivers")
@JsonPropertyOrder("id", "name", "dob", "nationality", "number", "code")
data class Drivers(
        @Id
        @Column(name = "driverid")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @JsonIgnore
        @Column(name = "driverref")
        val ref: String,
        val number: Int? = null,
        val code: String? = null,
        @JsonIgnore
        val forename: String,
        @JsonIgnore
        val surname: String,
        val dob: String? = null,
        val nationality: String? = null,
        @JsonIgnore
        val url: String
) {
        @JsonProperty("name")
        fun getFullname() = "$forename $surname"
}