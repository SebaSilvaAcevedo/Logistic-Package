package com.techacademy.logisticpackage.infrastructure.input.dto;

import com.techacademy.logisticpackage.commons.annotations.ValueOfEnum;
import com.techacademy.logisticpackage.commons.enums.PackageEnumsContainer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LpnDto {
    public String lpnId;
    @ValueOfEnum(enumClass = PackageEnumsContainer.PackageLpnType.class,
            message = "Not valid data in PackageDTO.status, must be CHILEXPRESS, BLUEXPRESS", allowNull = true)
    public String packageLpnType;
    public String packageLpnValue;
}
