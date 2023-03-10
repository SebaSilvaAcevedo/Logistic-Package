package com.techacademy.logisticpackage.infrastructure.input.dto;

import com.techacademy.logisticpackage.commons.annotations.ValueOfEnum;
import com.techacademy.logisticpackage.commons.enums.PackageEnumsContainer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelDto {
    public String labelId;
    @ValueOfEnum(enumClass = PackageEnumsContainer.PackageLabelType.class,
            message = "Not valid data in PackageDTO.status, must be THREE_PL, FALABELLA_GROUP", allowNull = true)
    public String packageLabelType;
    public String packageLabelValue;
    public String packageLabelFormat;

}
