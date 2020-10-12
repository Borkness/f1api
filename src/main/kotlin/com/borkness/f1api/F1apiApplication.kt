package com.borkness.f1api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
@EnableConfigurationProperties(JwtConfigProperties::class)
class F1apiApplication(val jwtConfigProperties: JwtConfigProperties) {
	@Bean
	fun bCryptPasswordEncoder() : BCryptPasswordEncoder {
		return BCryptPasswordEncoder()
	}
}

fun main(args: Array<String>) {
	runApplication<F1apiApplication>(*args)
}
