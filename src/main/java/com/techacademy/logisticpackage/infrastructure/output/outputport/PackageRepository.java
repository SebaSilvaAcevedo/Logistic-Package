package com.techacademy.logisticpackage.infrastructure.output.outputport;

import com.techacademy.logisticpackage.infrastructure.input.dto.PackageDto;
import com.techacademy.logisticpackage.infrastructure.output.entities.PackageEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository {
    public PackageEntity createPackage(PackageEntity packageEntity);
    public PackageEntity getById(String packageId);
}
