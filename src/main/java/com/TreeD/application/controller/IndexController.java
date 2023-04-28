package com.TreeD.application.controller;

import com.TreeD.application.service.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final ModelService modelService;

    public IndexController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping(value = {"", "/"})
    public String getModels(Model model) {

        model.addAttribute("models", modelService.getModels());

        return "front_page";
    }
}
