package com.borkness.f1api.repository

import com.borkness.f1api.models.Circuits
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface CircuitsRepository : JpaRepository<Circuits, Long>