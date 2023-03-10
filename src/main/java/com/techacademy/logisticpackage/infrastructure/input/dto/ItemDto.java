package com.techacademy.logisticpackage.infrastructure.input.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;
@Getter
@Setter
public class ItemDto {

    public String itemId;
    public String packageContentType;
    @NotNull(message = "OrderItemId attribute must not be null")
    @NotEmpty(message = "OrderItemId attribute must not be empty")
    public String orderItemId;
    @NotNull(message = "LogisticSkuId attribute must not be null")
    @NotEmpty(message = "LogisticSkuId attribute must not be empty")
    public String logisticSkuId;
    public String internalPackageId;
    public String offeringId;
    public String serialNumber;
    public String condition;
    @NotNull(message = "Quantity attribute must not be null")
    @Valid
    public QuantityNumberDto quantity;
    public ItemDimensionsDto itemDimensions;
    public Integer itemSize;
}
