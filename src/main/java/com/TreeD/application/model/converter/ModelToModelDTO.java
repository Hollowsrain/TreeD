package com.TreeD.application.model.converter;

import com.TreeD.application.model.DTO.ModelDTO;
import com.TreeD.application.model.entity.Model;
import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ModelToModelDTO implements Converter<Model, ModelDTO> {

    private final CommentToCommentDTO commentConverter;
    private final VehicleToVehicleDTO vehicleConverter;

    public ModelToModelDTO(CommentToCommentDTO commentConverter, VehicleToVehicleDTO vehicleConverter) {
        this.commentConverter = commentConverter;
        this.vehicleConverter = vehicleConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public ModelDTO convert(Model source) {
        if (source == null) {
            return null;
        }

        ModelDTO modelDTO = new ModelDTO();
        modelDTO.setId(source.getId());
        modelDTO.setUser(source.getUser());
        modelDTO.setModelName(source.getModelName());
        modelDTO.setCreatedAt(source.getCreatedAt());
        modelDTO.setModifiedAt(source.getModifiedAt());
        modelDTO.setDetails(source.getDetails());
        modelDTO.setPrice(source.getPrice().getAmount().toPlainString());

        modelDTO.setCarModel(vehicleConverter.convert(source.getCarModel()));

        if (source.getComments() != null && source.getComments().size() > 0) {
            source.getComments()
                    .forEach(comment -> modelDTO.getComments().add(commentConverter.convert(comment)));
        }

        return modelDTO;
    }
}
