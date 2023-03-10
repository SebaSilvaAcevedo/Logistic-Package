package com.techacademy.logisticpackage.application.mappers;

import com.techacademy.logisticpackage.infrastructure.input.dto.LabelDto;
import com.techacademy.logisticpackage.infrastructure.input.dto.PackageDto;
import com.techacademy.logisticpackage.infrastructure.output.entities.ItemEntity;
import com.techacademy.logisticpackage.infrastructure.output.entities.LabelEntity;
import com.techacademy.logisticpackage.infrastructure.output.entities.LpnEntity;
import com.techacademy.logisticpackage.infrastructure.output.entities.PackageEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DtoToEntity {
    public PackageEntity convert(PackageDto request){

        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setType(request.getType());
        packageEntity.setCondition(request.getCondition());
        packageEntity.setCreatedBy(request.getCreatedBy());
        packageEntity.setOrderRefType(request.getOrderRefType());
        packageEntity.setOrderRefId(request.getOrderRefId());
        packageEntity.setCreatedRefType(request.getCreatedRefType());
        packageEntity.setCreatedRefId(request.getCreatedRefId());
        packageEntity.setWeightInGrams(request.getWeightInGrams());
        packageEntity.setHeightInCm(request.getHeightInCm());
        packageEntity.setWidthInCm(request.getWidthInCm());
        packageEntity.setDepthInCm(request.getDepthInCm());
        packageEntity.setVolumeInDm3(request.getVolumeInDm3());
        // Headers
        packageEntity.setCountry(request.getCountry());
        packageEntity.setCommerce(request.getCommerce());

        // Label
        List<LabelEntity> packageLabelList = new ArrayList<>();
        if (request.getLabel() != null) {
            for (LabelDto label : request.getLabel()) {

                LabelEntity packageLabel = new LabelEntity();
                packageLabel.setPackageEntity(packageEntity);
                packageLabel.setPackageLabelType(label.getPackageLabelType());
                packageLabel.setPackageLabelValue(label.getPackageLabelValue());
                packageLabel.setPackageLabelFormat(label.getPackageLabelFormat());
                packageLabelList.add(packageLabel);
            }
        }
        packageEntity.setLabel(packageLabelList);

        // Lpn
        List<LpnEntity> packageLpnsList = new ArrayList<>();
        if(!Objects.isNull(request.getLpn())){
            request.getLpn().forEach(lpn -> {
                LpnEntity packageLpn = new LpnEntity();
                packageLpn.setPackageEntity(packageEntity);
                packageLpn.setPackageLpnType(lpn.getPackageLpnType());
                packageLpn.setPackageLpnValue(lpn.getPackageLpnValue());
                packageLpnsList.add(packageLpn);
            });
        }
        packageEntity.setLpn(packageLpnsList);

        // Items
        List<ItemEntity> items = new ArrayList<>();
        if (!Objects.isNull(request.getItems())){
            request.getItems().forEach(itemDto -> {
                ItemEntity item = new ItemEntity();
                item.setPackageEntity(packageEntity);
                item.setPackageContentType(itemDto.getPackageContentType());
                item.setOrderItemId(itemDto.getOrderItemId());
                item.setLogisticSkuId(itemDto.getLogisticSkuId());
                item.setInternalPackageId(itemDto.getInternalPackageId());
                item.setOfferingId(itemDto.getOfferingId());
                item.setSerialNumber(itemDto.getSerialNumber());
                item.setCondition(itemDto.getCondition());
                item.setQuantityNumber(itemDto.getQuantity().getQuantityNumber());
                item.setLengthInCm(itemDto.getItemDimensions().getLengthInCm());
                item.setWidthInCm(itemDto.getItemDimensions().getWidthInCm());
                item.setHeightInCm(itemDto.getItemDimensions().getHeightInCm());
                item.setWeightInKg(itemDto.getItemDimensions().getWeightInKg());
                item.setVolumeInDm3(itemDto.getItemDimensions().getVolumeInDm3());
                item.setItemSize(itemDto.getItemSize());
                items.add(item);
            });
        }
        packageEntity.setItems(items);
        return packageEntity;
    }
}
