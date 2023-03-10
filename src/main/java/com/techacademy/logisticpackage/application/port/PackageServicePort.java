package com.techacademy.logisticpackage.application.port;

import com.techacademy.logisticpackage.infrastructure.input.dto.PackageDto;
import com.techacademy.logisticpackage.infrastructure.input.dto.responses.PackagePostResponse;
import org.springframework.stereotype.Component;

@Component
public interface PackageServicePort {

        PackagePostResponse createPackage(PackageDto packageDto);

        PackageDto getById(String packageId);

}
