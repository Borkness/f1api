package com.borkness.f1api.security

import com.borkness.f1api.JwtConfigProperties
import com.borkness.f1api.services.AppUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurity(private val appUserDetailsService: AppUserDetailsService, private val bCryptPasswordEncoder: BCryptPasswordEncoder, private val jwtConfigProperties: JwtConfigProperties) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/signup").permitAll()
                .antMatchers("/error/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(JWTAuthenticationFilter(authenticationManager(), jwtConfigProperties))
                .addFilter(JWTAuthorizationFilter(authenticationManager(), jwtConfigProperties))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(appUserDetailsService).passwordEncoder(bCryptPasswordEncoder)
    }

    @Bean
    fun corsConfigurationSource() : CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val corsConfiguration : CorsConfiguration = CorsConfiguration().applyPermitDefaultValues()
        source.registerCorsConfiguration("/**", corsConfiguration)

        return source
    }
}