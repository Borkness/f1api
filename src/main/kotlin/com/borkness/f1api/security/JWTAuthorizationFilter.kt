package com.borkness.f1api.security

import com.borkness.f1api.JwtConfigProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager, private val jwtConfigProperties: JwtConfigProperties) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header : String? = request.getHeader("Authorization")
        if(header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response)
            return
        }

        val userAuthentication : UsernamePasswordAuthenticationToken? = getAuthentication(request)

        SecurityContextHolder.getContext().authentication = userAuthentication

        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest) : UsernamePasswordAuthenticationToken? {
        val token: String? = request.getHeader("Authorization")

        if(token != null) {
            val jwt = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtConfigProperties.secret.toByteArray()))
                    .build()
                    .parseClaimsJws(token.replace("Bearer ", ""))

            val user = jwt.body.subject

            val authorities : ArrayList<GrantedAuthority> = ArrayList()

            (jwt.body["auth"] as MutableList<*>).forEach {
                authorities.add(SimpleGrantedAuthority(it.toString()))
            }

            if(user != null) {
                return UsernamePasswordAuthenticationToken(user, null, authorities)
            }

            return null
        }

        return null

    }
}