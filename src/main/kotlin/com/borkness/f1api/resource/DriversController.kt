package com.borkness.f1api.resource

import com.borkness.f1api.models.Drivers
import com.borkness.f1api.repository.DriversRepository
import com.borkness.f1api.utilities.DataWrapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/drivers")
class DriversController(private val driversRepository: DriversRepository) {

    @GetMapping
    fun getDrivers() : ResponseEntity<MutableList<Drivers>> {
        val drivers = driversRepository.findAll()
        return ResponseEntity.ok(drivers)
    }

    @GetMapping("/{id}")
    fun getDriverById(@PathVariable(value = "id") driverId : Long) : ResponseEntity<DataWrapper<Drivers>> {
        return driversRepository.findById(driverId).map { driver ->
            val driverWrapper : DataWrapper<Drivers> = DataWrapper(driver)
            ResponseEntity.ok(driverWrapper)
        }.orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/ref/{ref}")
    fun getDriverByDriverRef(@PathVariable(value = "ref") driverRef : String) : ResponseEntity<DataWrapper<Drivers>> {
        return driversRepository.findByRef(driverRef).map { driver ->
            val driverWrapper : DataWrapper<Drivers> = DataWrapper(driver)
            ResponseEntity.ok(driverWrapper)
        }.orElse(ResponseEntity.notFound().build())
    }
}