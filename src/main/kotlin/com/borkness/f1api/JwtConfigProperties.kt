package com.borkness.f1api

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt-token")
class JwtConfigProperties {
    var expiration  = 0
    var secret = ""
}