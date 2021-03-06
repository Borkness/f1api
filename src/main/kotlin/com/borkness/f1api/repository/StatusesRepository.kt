package com.borkness.f1api.repository

import com.borkness.f1api.models.Statuses
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface StatusesRepository : JpaRepository<Statuses, Long>