package com.techacademy.logisticpackage.infrastructure.output.outputadapter;
import com.techacademy.logisticpackage.infrastructure.output.entities.PackageEntity;
import com.techacademy.logisticpackage.infrastructure.output.outputadapter.jpa.PackageJpaRepository;
import com.techacademy.logisticpackage.infrastructure.output.outputport.PackageRepository;
import com.techacademy.logisticpackage.application.mappers.DtoToEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PackagePersistencyAdapter implements PackageRepository {
    @Autowired
    private PackageJpaRepository jpaRepository;

    @Autowired
    DtoToEntity dtoToEntity;

    @Override
    public PackageEntity createPackage(PackageEntity packageEntity) {
        try {
            PackageEntity response =jpaRepository.save(packageEntity);
            return response;
        }
        catch (Exception e){
            log.error("{}, Error creating the package",e);
            throw e;
        }

    }

    @Override
    public PackageEntity getById(String packageId) {
        try{
        PackageEntity response = jpaRepository.findById(packageId).get();
        return response;
        }
        catch (Exception e){
            log.error("{}, Error retrieving package information by ID {}", e, packageId);
            throw e;
        }
    }
}
