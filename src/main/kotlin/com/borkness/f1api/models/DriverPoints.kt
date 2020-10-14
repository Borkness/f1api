package com.borkness.f1api.models

import com.fasterxml.jackson.annotation.JsonProperty

interface DriverPoints {
    val season: Int
    val points: Int
    val constructor: String
}