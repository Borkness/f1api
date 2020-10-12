package com.borkness.f1api.services

import com.borkness.f1api.models.Users
import com.borkness.f1api.repository.UsersRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AppUserDetailsService(private val usersRepository: UsersRepository) : UserDetailsService {
    override fun loadUserByUsername(s: String): UserDetails {
        val user : Users = usersRepository.findByUsername(s)

        val authorities = ArrayList<GrantedAuthority>()
        user.roles!!.forEach {roles ->
            authorities.add(SimpleGrantedAuthority(roles.name))
        }

        return User (
                user.username,
                user.password,
                authorities
        )
    }
}