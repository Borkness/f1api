package com.borkness.f1api.services

import com.borkness.f1api.models.UserRoles
import com.borkness.f1api.repository.UserRolesRepository
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class UserRolesService(private val userRolesRepository: UserRolesRepository) {

    @Transactional
    fun setUsersRole(userId: Long) : UserRoles {
        val userRole = UserRoles(userId = userId, roleId = 2)
        return userRolesRepository.save(userRole)
    }
}