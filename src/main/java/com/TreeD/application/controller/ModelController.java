package com.TreeD.application.controller;

import com.TreeD.application.model.DTO.ModelDTO;
import com.TreeD.application.model.DTO.VehicleMakes;
import com.TreeD.application.service.ModelService;
import com.TreeD.application.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping(value = "/model")
public class ModelController {

    private final ModelService modelService;
    private final VehicleService vehicleService;

    public ModelController(ModelService modelService, VehicleService vehicleService) {
        this.modelService = modelService;
        this.vehicleService = vehicleService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id", "createdAt", "modifiedAt");
    }

    @GetMapping(value = "/{id}/show")
    public String getModelById(@PathVariable String id, Model model) {
        model.addAttribute("model", modelService.getModelById(Long.valueOf(id)));
        model.addAttribute("rating", modelService.getModelRating(Long.valueOf(id)));

        return "model/show";
    }

    @GetMapping(value = "/upload")
    public String getUploadForm(Model model) {
        model.addAttribute("ModelFormObject", new ModelDTO());
        model.addAttribute("makes", Arrays.stream(VehicleMakes.values()).toList());

        return "model/upload_3d_model";
    }

    @GetMapping(value = "/get/{make}/model-list")
    public String getMakesModels(@PathVariable @Valid VehicleMakes make, Model model) {
        //todo:add validation for incorrect make
        model.addAttribute("models", vehicleService.getVehicleByMake(make));

        return "model/models_fragment :: modelSelectionList";
    }

    @PostMapping(value = "/upload")
    public String upload3dModel(@ModelAttribute("ModelFormObject") ModelDTO model) {
        modelService.createModel(model);
        return "redirect:/";
    }
}
