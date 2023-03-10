package com.techacademy.logisticpackage.infrastructure.output.outputadapter.jpa;

import com.techacademy.logisticpackage.infrastructure.output.entities.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageJpaRepository extends JpaRepository<PackageEntity,String> {
}
