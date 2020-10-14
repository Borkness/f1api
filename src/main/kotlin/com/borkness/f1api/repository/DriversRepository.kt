package com.borkness.f1api.repository

import com.borkness.f1api.models.DriverPoints
import com.borkness.f1api.models.Drivers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional
import kotlin.collections.ArrayList

@Repository
@Transactional
interface DriversRepository : JpaRepository<Drivers, Long> {
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    fun findByRef(ref: String) : Optional<Drivers>

    @Query("SELECT `races`.`year` AS season, SUM(`results`.`points`) AS points, `constructors`.`name` AS constructor " +
            "FROM `results` JOIN `races` ON `races`.`raceId` = `results`.`raceId` JOIN `constructors` " +
            "ON `constructors`.`constructorId` = `results`.`constructorId` JOIN `drivers` ON `drivers`.`driverId` = `results`.`driverId` " +
            "WHERE `drivers`.`driverId` = ? GROUP BY `drivers`.`surname`, `constructors`.`name`, `races`.`year` ORDER BY `races`.`year`;", nativeQuery = true)
    fun listAllResultsFromId(id: Long) : List<DriverPoints>
}