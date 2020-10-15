package com.borkness.f1api.repository

import com.borkness.f1api.models.UserRoles
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRolesRepository : JpaRepository<UserRoles, Long>