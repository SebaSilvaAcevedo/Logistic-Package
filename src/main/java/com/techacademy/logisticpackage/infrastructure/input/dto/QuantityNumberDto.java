package com.techacademy.logisticpackage.infrastructure.input.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class QuantityNumberDto {
    @NotNull(message = "QuantityNumber attribute must not be null")
    public Integer quantityNumber;
}
