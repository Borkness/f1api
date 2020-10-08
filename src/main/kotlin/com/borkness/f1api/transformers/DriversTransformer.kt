package com.borkness.f1api.transformers

import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("id", "name", "nationality", "dateOfBirth" , "code" , "number")
data class DriversTransformer(
        val id : Long,
        private val forename : String,
        private val surname : String,
        val number : Int? = null,
        val code : String? = null,
        val dateOfBirth : String? = null,
        val nationality : String? = null
) {
    fun getName() = "$forename $surname"
}