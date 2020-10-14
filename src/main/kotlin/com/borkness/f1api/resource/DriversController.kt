package com.borkness.f1api.resource

import com.borkness.f1api.models.DriverPoints
import com.borkness.f1api.models.Drivers
import com.borkness.f1api.repository.DriversRepository
import com.borkness.f1api.utilities.DataWrapper
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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
    fun getDrivers() : ResponseEntity<DataWrapper<MutableList<Drivers>>> {
        val drivers = driversRepository.findAll()
        val driverWrapper : DataWrapper<MutableList<Drivers>> = DataWrapper(drivers)
        return ResponseEntity.ok(driverWrapper)
    }

    /**
     * Get driver by id
     *
     * Get a resource of a driver based on id
     *
     * @param driverId Driver id in database
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER', 'NORMAL_USER')")
    fun getDriverById(@PathVariable(value = "id") driverId : Long) : ResponseEntity<DataWrapper<Drivers>> {
        return driversRepository.findById(driverId).map { driver ->
            val driverWrapper : DataWrapper<Drivers> = DataWrapper(driver)
            ResponseEntity.ok(driverWrapper)
        }.orElse(ResponseEntity.notFound().build())
    }

    /**
     * Get driver points total
     *
     * Get a resource with a set of a specific drivers points for all seasons they competed
     *
     * @param driverId Driver id in database
     */
    @GetMapping("/{id}/results")
    fun getDriverResultsById(@PathVariable(value = "id") driverId: Long) : ResponseEntity<DataWrapper<List<DriverPoints>>> {
        val driverPoints = driversRepository.listAllResultsFromId(driverId)

        return ResponseEntity.ok(DataWrapper(driverPoints))
    }
}