package com.borkness.f1api.utilities

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Data wrapper
 *
 * Wraps a Json Resource with a key returning a list.
 *
 * @param T the type of resource
 * @property data
 */
class DataWrapper<T>(data : T) {
    private var data : List<T> = emptyList()
    init {
        this.data = listOf(data)
    }

    @JsonProperty("content")
    fun getData() : List<T> {
        return data
    }
}