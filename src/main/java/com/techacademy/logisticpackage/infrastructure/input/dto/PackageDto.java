package com.techacademy.logisticpackage.infrastructure.input.dto;


import com.techacademy.logisticpackage.commons.annotations.ValueOfEnum;
import com.techacademy.logisticpackage.commons.enums.PackageEnumsContainer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@Getter
@Setter
public class PackageDto {
    public String packageId;
    @NotNull(message = "Type attribute must not be null")
    @NotEmpty(message = "Type attribute must not be empty")
    @ValueOfEnum(enumClass = PackageEnumsContainer.PackageType.class,
            message = "Not valid data in PackageDTO.type, must be BOX or PALLET", allowNull = false)
    public String type;
    public String condition;
    @NotNull(message = "CreatedBy attribute must not be null")
    @NotEmpty(message = "CreatedBy attribute must not be empty")
    public String createdBy;
    public String orderRefType;
    @NotNull(message = "OrderRefId attribute must not be null")
    @NotEmpty(message = "OrderRefId attribute must not be empty")
    public String orderRefId;
    @NotEmpty(message = "CreatedRefType attribute must not be empty")
    @ValueOfEnum(enumClass = PackageEnumsContainer.CreatedRefType.class,
            message = "Not valid data in PackageDTO.status, must be  FO, RLO, STO", allowNull = false)
    public String createdRefType;
    @NotNull(message = "CreatedRefId attribute must not be null")
    @NotEmpty(message = "CreatedRefId attribute must not be empty")
    public String createdRefId;
    public Integer weightInGrams;
    public Integer heightInCm;
    public Integer widthInCm;
    public Integer depthInCm;
    public Integer volumeInDm3;
    @Valid
    public List<LpnDto> lpn;
    @Valid
    public List<LabelDto> label;
    @NotNull(message = "Items attribute must not be null")
    @NotEmpty(message = "Items attribute must not be empty")
    @Valid
    public List<ItemDto> items;
    @ValueOfEnum(enumClass = PackageEnumsContainer.Country.class,
            message = "Not valid data in PackageDTO.xCountry, must be CL, AR, PE, CO, MX", allowNull = true)
    public String country;
    @ValueOfEnum(enumClass = PackageEnumsContainer.Commerce.class,
            message = "Not valid data in PackageDTO.xCountry, must be SODIMAC, TOTTUS, FALABELLA, LINIO, IKEA", allowNull = true)
    public String commerce;

}
