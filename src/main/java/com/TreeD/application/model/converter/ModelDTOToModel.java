package com.TreeD.application.model.converter;

import com.TreeD.application.model.DTO.ModelDTO;
import com.TreeD.application.model.entity.Model;
import com.TreeD.application.model.entity.Price;
import com.TreeD.application.model.entity.Vehicle;
import com.TreeD.application.service.VehicleService;
import com.TreeD.application.util.ApplicationUtils;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ModelDTOToModel implements Converter<ModelDTO, Model> {

    private final CommentDTOToComment commentConverter;
    private final ApplicationUtils applicationUtils;
    private final VehicleService vehicleService;

    @Synchronized
    @Nullable
    @Override
    public Model convert(ModelDTO source) {
        if (source == null) {
            return null;
        }

        Price sourcePrice = new Price();
        sourcePrice.setAmount(BigDecimal.valueOf(Long.parseLong(source.getPrice())));
        sourcePrice.setCurrency(applicationUtils.getUserCurrency());

        Model model = new Model();
        model.setId(source.getId());
        model.setModelName(source.getModelName());
        model.setUser(applicationUtils.getCurrentUser());
        model.setModel(applicationUtils.convertMultiPartToBytes(source.getModel()));
        model.setImage(applicationUtils.convertMultiPartToBytes(source.getImage()));
        model.setCreatedAt(source.getCreatedAt());
        model.setModifiedAt(source.getModifiedAt());
        model.setDetails(source.getDetails());
        model.setPrice(sourcePrice);

        model.setCarModel(vehicleService.getVehicleById(Long.valueOf(source.getCarModelId())));

        if (source.getComments() != null && source.getComments().size() > 0) {
            source.getComments()
                    .forEach(comment -> model.getComments().add(commentConverter.convert(comment)));
        }

        return model;
    }
}
