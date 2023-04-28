package com.TreeD.application.model.converter;

import com.TreeD.application.model.DTO.VehicleDTO;
import com.TreeD.application.model.entity.Vehicle;
import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VehicleToVehicleDTO implements Converter<Vehicle, VehicleDTO> {

    @Synchronized
    @Nullable
    @Override
    public VehicleDTO convert(Vehicle source) {
        if (source == null) {
            return null;
        }

        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(source.getId());
        vehicleDTO.setModifiedAt(source.getModifiedAt());
        vehicleDTO.setCreatedAt(source.getCreatedAt());
        vehicleDTO.setModel(source.getModel());
        vehicleDTO.setMake(source.getMake());
        vehicleDTO.setStartYear(source.getStartYear());
        vehicleDTO.setEndYear(source.getEndYear());
        vehicleDTO.setInternalBodyCode(source.getInternalBodyCode());

        return vehicleDTO;
    }
}
