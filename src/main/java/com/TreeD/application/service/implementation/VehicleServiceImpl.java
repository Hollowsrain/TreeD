package com.TreeD.application.service.implementation;

import com.TreeD.application.model.DTO.VehicleDTO;
import com.TreeD.application.model.converter.VehicleDTOToVehicle;
import com.TreeD.application.model.converter.VehicleToVehicleDTO;
import com.TreeD.application.model.entity.Vehicle;
import com.TreeD.application.model.DTO.VehicleMakes;
import com.TreeD.application.repository.VehicleRepository;
import com.TreeD.application.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleToVehicleDTO vehicleToVehicleDTO;
    private final VehicleDTOToVehicle vehicleDTOToVehicle;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleToVehicleDTO vehicleToVehicleDTO, VehicleDTOToVehicle vehicleDTOToVehicle) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleToVehicleDTO = vehicleToVehicleDTO;
        this.vehicleDTOToVehicle = vehicleDTOToVehicle;
    }


    @Override
    public List<VehicleDTO> getVehicleByMake(VehicleMakes vehicleMakes) {
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();

        for (Vehicle vehicle: vehicleRepository.findAllByMake(vehicleMakes.name())) {
            vehicleDTOList.add(vehicleToVehicleDTO.convert(vehicle));
        }
        return vehicleDTOList;
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).get();
    }
}
