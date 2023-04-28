package com.TreeD.application.model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Getter
@Setter
public class VehicleDTO {

    private Long id;

    private ZonedDateTime createdAt;

    private ZonedDateTime modifiedAt;

    private String make;

    private String startYear;

    private String endYear;

    private String model;

    private String internalBodyCode;
}