package com.borkness.f1api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class F1apiApplication

fun main(args: Array<String>) {
	runApplication<F1apiApplication>(*args)
}
