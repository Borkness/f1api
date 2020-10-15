package com.borkness.f1api.resource

import com.borkness.f1api.models.Users
import com.borkness.f1api.repository.UserRolesRepository
import com.borkness.f1api.repository.UsersRepository
import com.borkness.f1api.services.UserRolesService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/")
class AuthController(private val usersRepository: UsersRepository, private val userRolesService: UserRolesService, private val bCryptPasswordEncoder: BCryptPasswordEncoder) {
    @PostMapping("/signup")
    fun signUpToApi(@RequestBody users: Users) : String {
        users.password = bCryptPasswordEncoder.encode(users.password)
        usersRepository.save(users)
        userRolesService.setUsersRole(users.id)
        return "OK"
    }
}