package com.TreeD.application.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
@ToString(callSuper = true)
public class Vehicle extends BaseEntity{

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "start_year", nullable = false)
    private String startYear;

    @Column(name = "end_year")
    private String endYear;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "internal_body_code")
    private String internalBodyCode;


}
