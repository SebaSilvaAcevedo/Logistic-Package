package com.techacademy.logisticpackage.application.mappers;

import com.techacademy.logisticpackage.infrastructure.input.dto.*;
import com.techacademy.logisticpackage.infrastructure.output.entities.LabelEntity;
import com.techacademy.logisticpackage.infrastructure.output.entities.PackageEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class EntityToDto {
    public PackageDto convert(PackageEntity request) {
        PackageDto packageDto = new PackageDto();

        packageDto.setPackageId(request.getPackageId());
        packageDto.setType(request.getType());
        packageDto.setCondition(request.getCondition());
        packageDto.setCreatedBy(request.getCreatedBy());
        packageDto.setOrderRefType(request.getOrderRefType());
        packageDto.setOrderRefId(request.getOrderRefId());
        packageDto.setCreatedRefType(request.getCreatedRefType());
        packageDto.setCreatedRefId(request.getCreatedRefId());
        packageDto.setWeightInGrams(request.getWeightInGrams());
        packageDto.setHeightInCm(request.getHeightInCm());
        packageDto.setWidthInCm(request.getWidthInCm());
        packageDto.setDepthInCm(request.getDepthInCm());
        packageDto.setVolumeInDm3(request.getVolumeInDm3());
        // Headers
        packageDto.setCountry(request.getCountry());
        packageDto.setCommerce(request.getCommerce());

        // Label
        List<LabelDto> packageLabelList = new ArrayList<>();
        if (request.getLabel() != null) {
            for (LabelEntity label : request.getLabel()) {

                LabelDto packageLabel = new LabelDto();
                packageLabel.setLabelId(label.getLabelId());
                packageLabel.setPackageLabelType(label.getPackageLabelType());
                packageLabel.setPackageLabelValue(label.getPackageLabelValue());
                packageLabel.setPackageLabelFormat(label.getPackageLabelFormat());
                packageLabelList.add(packageLabel);
            }
        }
        packageDto.setLabel(packageLabelList);

        // Lpn
        List<LpnDto> packageLpnsList = new ArrayList<>();
        if (!Objects.isNull(request.getLpn())) {
            request.getLpn().forEach(lpn -> {
                LpnDto packageLpn = new LpnDto();
                packageLpn.setLpnId(lpn.getLpnId());
                packageLpn.setPackageLpnType(lpn.getPackageLpnType());
                packageLpn.setPackageLpnValue(lpn.getPackageLpnValue());
                packageLpnsList.add(packageLpn);
            });
        }
        packageDto.setLpn(packageLpnsList);

        // Items
        List<ItemDto> items = new ArrayList<>();
        if (!Objects.isNull(request.getItems())) {
            request.getItems().forEach(itemEntity -> {
                ItemDto item = new ItemDto();
                item.setItemId(itemEntity.getItemId());
                item.setPackageContentType(itemEntity.getPackageContentType());
                item.setOrderItemId(itemEntity.getOrderItemId());
                item.setLogisticSkuId(itemEntity.getLogisticSkuId());
                item.setInternalPackageId(itemEntity.getInternalPackageId());
                item.setOfferingId(itemEntity.getOfferingId());
                item.setSerialNumber(itemEntity.getSerialNumber());
                item.setCondition(itemEntity.getCondition());
                QuantityNumberDto quantityNumberDto = new QuantityNumberDto();
                quantityNumberDto.setQuantityNumber(itemEntity.getQuantityNumber());
                item.setQuantity(quantityNumberDto);
                ItemDimensionsDto itemDimensionsDto = new ItemDimensionsDto();
                itemDimensionsDto.setLengthInCm(itemEntity.getLengthInCm());
                itemDimensionsDto.setWidthInCm(itemEntity.getWidthInCm());
                itemDimensionsDto.setHeightInCm(itemEntity.getHeightInCm());
                itemDimensionsDto.setWeightInKg(itemEntity.getWeightInKg());
                itemDimensionsDto.setVolumeInDm3(itemEntity.getVolumeInDm3());
                item.setItemDimensions(itemDimensionsDto);
                item.setItemSize(itemEntity.getItemSize());
                items.add(item);
            });
        }
        packageDto.setItems(items);
        return packageDto;
    }
}
