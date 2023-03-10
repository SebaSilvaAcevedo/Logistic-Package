package com.techacademy.logisticpackage.application.services;

import com.techacademy.logisticpackage.application.mappers.DtoToEntity;
import com.techacademy.logisticpackage.application.mappers.EntityToDto;
import com.techacademy.logisticpackage.application.port.PackageServicePort;
import com.techacademy.logisticpackage.commons.utils.PackageUtils;
import com.techacademy.logisticpackage.infrastructure.input.dto.PackageDto;
import com.techacademy.logisticpackage.infrastructure.input.dto.responses.PackagePostResponse;
import com.techacademy.logisticpackage.infrastructure.output.outputport.OutBoundChannelGcp;
import com.techacademy.logisticpackage.infrastructure.output.outputport.PackageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@Log4j2
public class PackageService implements PackageServicePort {
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private EntityToDto entityToDto;
    @Autowired
    private DtoToEntity dtoToEntity;
    @Autowired
    private PackageUtils utils;
    @Autowired
    private OutBoundChannelGcp outBoundChannel;
    @Override
    public PackagePostResponse createPackage(@Valid PackageDto request) {
        PackageDto packageDto = entityToDto.convert(packageRepository.createPackage(dtoToEntity.convert(request)));
        log.info("Package Created, Data:{\"packageId\":\"{}\"}", packageDto.getPackageId());
        outBoundChannel.sendMsgToTopic1(utils.toJson(packageDto));
        PackagePostResponse response = new PackagePostResponse(packageDto.getPackageId());
        outBoundChannel.sendMsgToTopic2(utils.toJson(response));
        return response;
    }
    @Override
    public PackageDto getById(String packageId) {
        PackageDto packageDto = entityToDto.convert(packageRepository.getById(packageId));
        log.info("Package Found, Data: {}", utils.toJson(packageDto));
        return packageDto;
    }
}
