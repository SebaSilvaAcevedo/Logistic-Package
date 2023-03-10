package com.techacademy.logisticpackage.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.techacademy.logisticpackage.application.mappers.DtoToEntity;
import com.techacademy.logisticpackage.application.mappers.EntityToDto;
import com.techacademy.logisticpackage.commons.utils.PackageUtils;
import com.techacademy.logisticpackage.infrastructure.input.dto.*;
import com.techacademy.logisticpackage.infrastructure.output.entities.PackageEntity;
import com.techacademy.logisticpackage.infrastructure.output.outputport.OutBoundChannelGcp;
import com.techacademy.logisticpackage.infrastructure.output.outputport.PackageRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PackageService.class})
@ExtendWith(SpringExtension.class)
class PackageServiceTest {
    @MockBean
    private DtoToEntity dtoToEntity;

    @MockBean
    private EntityToDto entityToDto;

    private DtoToEntity entityMapper;

    @MockBean
    private OutBoundChannelGcp outBoundChannelGcp;

    @MockBean
    private PackageRepository packageRepository;

    @Autowired
    private PackageService packageService;

    @MockBean
    private PackageUtils packageUtils;

    private PackageDto packageDto;

    @BeforeEach
    void beforeEach() {
        entityMapper = new DtoToEntity();
        packageDto = new PackageDto();
        packageDto.setPackageId("PKG0000000001");
        packageDto.setType("BOX");
        packageDto.setCondition("OK");
        packageDto.setCreatedBy("RLO");
        packageDto.setOrderRefType("RLO");
        packageDto.setOrderRefId("RLO00000004L9");
        packageDto.setCreatedRefType("RLO");
        packageDto.setCreatedRefId("RLO0000000009-TEST-REDIS");
        packageDto.setWeightInGrams(5000);
        packageDto.setHeightInCm(50);
        packageDto.setWidthInCm(50);
        packageDto.setDepthInCm(50);
        packageDto.setVolumeInDm3(125);
        // Lpn
        List<LpnDto> packageLpnsList = new ArrayList<>();
        LpnDto packageLpn = new LpnDto();
        packageLpn.setPackageLpnType("CHILEXPRESS");
        packageLpn.setPackageLpnValue("993989833980001");
        packageLpnsList.add(packageLpn);
        packageDto.setLpn(packageLpnsList);
        // Labels
        List<LabelDto> packageLabelList = new ArrayList<>();
        LabelDto packageLabel = new LabelDto();
        packageLabel.setPackageLabelType("THREE_PL");
        packageLabel.setPackageLabelValue("993432342480001");
        packageLabel.setPackageLabelFormat("PDF");
        packageLabelList.add(packageLabel);
        packageDto.setLabel(packageLabelList);
        // Items
        ItemDto item = new ItemDto();
        item.setPackageContentType("Fragile");
        item.setOrderItemId("4ef624a4-af49-4477-91b9-df7ed103a238");
        item.setLogisticSkuId("ca3bb03a-2974-457f-9fcc-6e4077e5d060");
        item.setInternalPackageId("InternalPackageId");
        item.setOfferingId("GENERICO_SODIMAC");
        item.setSerialNumber("4CE0460D0G");
        item.setCondition("OK");
        QuantityNumberDto quantityNumberDto = new QuantityNumberDto();
        quantityNumberDto.setQuantityNumber(3);
        item.setQuantity(quantityNumberDto);
        ItemDimensionsDto itemDimensionsDto = new ItemDimensionsDto();
        itemDimensionsDto.setLengthInCm(15);
        itemDimensionsDto.setWidthInCm(15);
        itemDimensionsDto.setHeightInCm(15);
        itemDimensionsDto.setWeightInKg(1);
        itemDimensionsDto.setVolumeInDm3(4);
        item.setItemDimensions(itemDimensionsDto);
        item.setItemSize(50);
        List<ItemDto> items = new ArrayList<>();
        items.add(item);
        packageDto.setItems(items);
    }

    @Test
    void testCreatePackage() {
        PackageEntity packageEntity = entityMapper.convert(packageDto);
        when(dtoToEntity.convert((PackageDto) any())).thenReturn(packageEntity);
        when(entityToDto.convert((PackageEntity) any())).thenReturn(packageDto);
        doNothing().when(outBoundChannelGcp).sendMsgToTopic1((String) any());
        doNothing().when(outBoundChannelGcp).sendMsgToTopic2((String) any());
        when(packageRepository.createPackage((PackageEntity) any())).thenReturn(packageEntity);
        assertEquals("PKG0000000001", packageService.createPackage(packageDto).getPackageId());
        verify(dtoToEntity).convert((PackageDto) any());
        verify(entityToDto).convert((PackageEntity) any());
        verify(outBoundChannelGcp).sendMsgToTopic1((String) any());
        verify(outBoundChannelGcp).sendMsgToTopic2((String) any());
        verify(packageRepository).createPackage((PackageEntity) any());
    }

    @Test
    void testGetById() {
        PackageEntity packageEntity = entityMapper.convert(packageDto);
        when(entityToDto.convert((PackageEntity) packageEntity )).thenReturn(packageDto);
        when(packageRepository.getById((String) "PKG0000000002")).thenReturn(packageEntity);
        assertSame(packageDto, packageService.getById("PKG0000000002"));
        verify(entityToDto).convert((PackageEntity) any());
        verify(packageRepository).getById((String) any());
    }
}


