package com.TreeD.application.model.DTO;

import com.TreeD.application.model.entity.Price;
import com.TreeD.application.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ModelDTO {

    private Long id;

    private ZonedDateTime createdAt;

    private ZonedDateTime modifiedAt;

    private User user;

    private String modelName;

    private String price;

    private MultipartFile image;

    private List<CommentDTO> comments;

    private String details;

    private MultipartFile model;

    private VehicleDTO carModel;

    private String carModelId;
}
