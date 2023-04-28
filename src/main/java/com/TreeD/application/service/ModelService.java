package com.TreeD.application.service;

import com.TreeD.application.model.DTO.ModelDTO;
import com.TreeD.application.model.entity.Model;

import java.util.List;

public interface ModelService {

    List<ModelDTO> getModelsByUserId(Long id);

    List<Model> getModels();

    ModelDTO createModel(ModelDTO modelDTO);

    Model updateModelById(Long id, Model model);

    Model getModelById(Long id);

    void deleteModelById(Long id);

    Double getModelRating(Long id);
}
