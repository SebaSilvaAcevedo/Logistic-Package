package com.techacademy.logisticpackage.infrastructure.input.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDimensionsDto {
    public Integer lengthInCm;
    public Integer widthInCm;
    public Integer heightInCm;
    public Integer weightInKg;
    public Integer volumeInDm3;
}
