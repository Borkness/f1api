package com.borkness.f1api.repository

import com.borkness.f1api.models.Constructors
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface ConstructorsRepository : JpaRepository<Constructors, Long>