package com.TreeD.application.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
public class Price {
    private BigDecimal amount;
    private String currency;
}
