package com.borkness.f1api.resource

import com.borkness.f1api.transformers.DriversTransformer
import com.borkness.f1api.repository.DriversRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/drivers")
class DriversController(private val driversRepository: DriversRepository) {

    @GetMapping
    fun getDrivers() : ResponseEntity<MutableList<DriversTransformer>> {
        val drivers = driversRepository.findAll()
        val driverList: MutableList<DriversTransformer> = mutableListOf()
        for (driver in drivers) {
            driverList.add(DriversTransformer(driver.id,
                    driver.forename,
                    driver.surname,
                    driver.number,
                    driver.code,
                    driver.dob,
                    driver.nationality))
        }
        return ResponseEntity.ok(driverList)
    }

    @GetMapping("/{id}")
    fun getDriverById(@PathVariable(value = "id") driverId : Long) : ResponseEntity<DriversTransformer> {
        return driversRepository.findById(driverId).map { driver ->
            val driverRes = DriversTransformer(
                    driver.id,
                    driver.forename,
                    driver.surname,
                    driver.number,
                    driver.code,
                    driver.dob,
                    driver.nationality
            )
            ResponseEntity.ok(driverRes)
        }.orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/ref/{ref}")
    fun getDriverByDriverRef(@PathVariable(value = "ref") driverRef : String) : ResponseEntity<DriversTransformer> {
        return driversRepository.findByRef(driverRef).map { driver ->
            val driverRes = DriversTransformer(
                    driver.id,
                    driver.forename,
                    driver.surname,
                    driver.number,
                    driver.code,
                    driver.dob,
                    driver.nationality
            )
            ResponseEntity.ok(driverRes)
        }.orElse(ResponseEntity.notFound().build())
    }
}