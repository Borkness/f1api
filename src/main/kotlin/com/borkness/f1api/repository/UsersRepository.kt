package com.borkness.f1api.repository

import com.borkness.f1api.models.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface UsersRepository : JpaRepository<Users, Long> {
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    fun findByUsername(username : String) : Users
}