package com.TreeD.application.service;

import com.TreeD.application.model.DTO.VehicleDTO;
import com.TreeD.application.model.entity.Vehicle;
import com.TreeD.application.model.DTO.VehicleMakes;

import java.util.List;

public interface VehicleService {

    List<VehicleDTO> getVehicleByMake(VehicleMakes vehicleMakes);

    Vehicle getVehicleById(Long id);
}
