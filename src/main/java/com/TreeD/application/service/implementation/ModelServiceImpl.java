package com.TreeD.application.service.implementation;

import com.TreeD.application.exceptions.ModelNotFoundException;
import com.TreeD.application.model.DTO.ModelDTO;
import com.TreeD.application.model.converter.ModelDTOToModel;
import com.TreeD.application.model.converter.ModelToModelDTO;
import com.TreeD.application.model.entity.Comment;
import com.TreeD.application.model.entity.Model;
import com.TreeD.application.repository.ModelRepository;
import com.TreeD.application.service.CommentService;
import com.TreeD.application.service.ModelService;
import com.TreeD.application.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final CommentService commentService;
    private final VehicleService vehicleService;
    private final ModelDTOToModel modelDTOToModel;
    private final ModelToModelDTO modelToModelDTO;

    public ModelServiceImpl(ModelRepository modelRepository, CommentService commentService, VehicleService vehicleService, ModelDTOToModel modelDTOToModel, ModelToModelDTO modelToModelDTO) {
        this.modelRepository = modelRepository;
        this.commentService = commentService;
        this.vehicleService = vehicleService;
        this.modelDTOToModel = modelDTOToModel;
        this.modelToModelDTO = modelToModelDTO;
    }

    @Override
    public List<ModelDTO> getModelsByUserId(Long id) {
        List<ModelDTO> modelDTOList = new ArrayList<>();

        for (Model model : modelRepository.findAllByUserId(id)) {
            modelDTOList.add(modelToModelDTO.convert(model));
        }
        return modelDTOList;
    }

    @Override
    public List<Model> getModels() {
        List<Model> models = new ArrayList<>();

        modelRepository.findAll().iterator().forEachRemaining(models::add);
        return models;
    }

    @Override
    public ModelDTO createModel(ModelDTO modelDTO) {
        Model model = modelDTOToModel.convert(modelDTO);

        model.setModifiedAt(ZonedDateTime.now());
        model.setCreatedAt(ZonedDateTime.now());

        return modelToModelDTO.convert(modelRepository.save(model));
    }

    @Override
    public Model updateModelById(Long id, Model model) {
        Model currentModel = getModelById(id);

        model.setId(currentModel.getId());
        model.setModifiedAt(ZonedDateTime.now());

        return modelRepository.save(model);
    }

    @Override
    public Model getModelById(Long id) {
        Optional<Model> model = modelRepository.findById(id);

        if (model.isEmpty()) {
            log.error("Model with id: " + id + " cannot be found.");
            throw new ModelNotFoundException("Model with cannot be found");
        }

        return model.get();
    }

    @Override
    public void deleteModelById(Long id) {
        modelRepository.deleteById(id);
    }

    public Double getModelRating(Long id) {
        Model model = getModelById(id);
        Double rating = 0.00;

        for (Comment comment : model.getComments()) {
            rating += comment.getRating();
        }

        return rating / model.getComments().size();
    }
}
