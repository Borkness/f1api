package com.borkness.f1api.utilities

import com.fasterxml.jackson.annotation.JsonProperty

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