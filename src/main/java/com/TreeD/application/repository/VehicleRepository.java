package com.TreeD.application.repository;

import com.TreeD.application.model.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    List<Vehicle> findAllByMake(String make);
}
