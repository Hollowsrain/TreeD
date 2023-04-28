package com.TreeD.application.model.converter;

import com.TreeD.application.model.DTO.VehicleDTO;
import com.TreeD.application.model.entity.Vehicle;
import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VehicleDTOToVehicle implements Converter<VehicleDTO, Vehicle> {

    @Synchronized
    @Nullable
    @Override
    public Vehicle convert(VehicleDTO source) {
        if (source == null) {
            return null;
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setId(source.getId());
        vehicle.setModifiedAt(source.getModifiedAt());
        vehicle.setCreatedAt(source.getCreatedAt());
        vehicle.setModel(source.getModel());
        vehicle.setMake(source.getMake());
        vehicle.setStartYear(source.getStartYear());
        vehicle.setEndYear(source.getEndYear());
        vehicle.setInternalBodyCode(source.getInternalBodyCode());

        return vehicle;
    }
}
