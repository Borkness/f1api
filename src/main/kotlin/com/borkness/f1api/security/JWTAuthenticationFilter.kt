package com.borkness.f1api.security

import com.borkness.f1api.JwtConfigProperties
import com.borkness.f1api.models.Users
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.ArrayList

class JWTAuthenticationFilter(@Autowired private val authenticationMngr: AuthenticationManager, private val jwtConfigProperties: JwtConfigProperties) : UsernamePasswordAuthenticationFilter() {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        try {
            val userCredentials : Users = ObjectMapper().readValue(request.inputStream, Users::class.java)

            return authenticationMngr.authenticate(
                    UsernamePasswordAuthenticationToken(
                            userCredentials.username,
                            userCredentials.password,
                            ArrayList<GrantedAuthority>()
                    )
            )
        } catch (ex : IOException) {
            throw AuthenticationServiceException(ex.message)
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication) {

        val claims: MutableList<String> = mutableListOf()
        if (authResult.authorities.isNotEmpty()) {
            authResult.authorities?.forEach { a -> claims.add(a.toString()) }
        }

        val token : String = Jwts.builder().setSubject((authResult.principal as User).username)
                .setExpiration(Date(System.currentTimeMillis() + jwtConfigProperties.expiration))
                .signWith(Keys.hmacShaKeyFor(jwtConfigProperties.secret.toByteArray()))
                .claim("auth", claims)
                .compact()

        val body : String = token

        response?.writer?.write(body)
        response?.writer?.flush()
    }
}