package com.borkness.f1api.resource

import com.borkness.f1api.models.Drivers
import com.borkness.f1api.repository.DriversRepository
import com.borkness.f1api.utilities.DataWrapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/drivers")
class DriversController(private val driversRepository: DriversRepository) {

    /**
     * Get all drivers
     *
     * Get a resource of all drivers
     */
    @GetMapping
    fun getDrivers() : ResponseEntity<MutableList<Drivers>> {
        val drivers = driversRepository.findAll()
        return ResponseEntity.ok(drivers)
    }

    /**
     * Get driver by id
     *
     * Get a resource of a driver based on id
     *
     * @param driverId Driver id in database
     */
    @GetMapping("/{id}")
    fun getDriverById(@PathVariable(value = "id") driverId : Long) : ResponseEntity<DataWrapper<Drivers>> {
        return driversRepository.findById(driverId).map { driver ->
            val driverWrapper : DataWrapper<Drivers> = DataWrapper(driver)
            ResponseEntity.ok(driverWrapper)
        }.orElse(ResponseEntity.notFound().build())
    }
}