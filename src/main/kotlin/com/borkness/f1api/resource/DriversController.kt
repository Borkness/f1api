package com.borkness.f1api.resource

import com.borkness.f1api.models.Drivers
import com.borkness.f1api.repository.DriversRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/drivers")
class DriversController(private val driversRepository: DriversRepository) {

    @GetMapping
    fun getDrivers() : List<Drivers> {
        return driversRepository.findAll()
    }
}