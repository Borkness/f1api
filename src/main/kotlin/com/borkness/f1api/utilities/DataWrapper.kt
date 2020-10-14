package com.borkness.f1api.utilities

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Data wrapper
 *
 * Wraps a Json Resource with a key returning a list.
 *
 * @param T the type of resource
 * @property dataToWrap
 */
class DataWrapper <T>(private val dataToWrap : T){
    @JsonProperty("content")
    fun getData() : T {
        return dataToWrap
    }
}