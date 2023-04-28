package com.TreeD.application.service.implementation;

import com.TreeD.application.model.entity.Model;
import com.TreeD.application.repository.ModelRepository;
import com.TreeD.application.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {


    private final ModelRepository modelRepository;

    public ImageServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public void saveImageFile(Long id, MultipartFile image) {
        try {
            Model model = modelRepository.findById(id).get();

            Byte[] imageBytes = new Byte[image.getBytes().length];

            int i = 0;
            for (byte b : image.getBytes()) {
                imageBytes[i++] = b;
            }
            model.setImage(imageBytes);
            modelRepository.save(model);
        } catch (IOException ioe) {
            log.error("Error while saving an image for model with id: " + id);
            throw new RuntimeException("Failed to update image");
        }
    }
}
