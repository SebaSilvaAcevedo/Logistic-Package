package com.techacademy.logisticpackage.infrastructure.output.entities;

import com.techacademy.logisticpackage.commons.generators.PackageIdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "package")
public class PackageEntity {
    @Id
    @Column(name = "packageId", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "package_seq")
    @GenericGenerator(
            name = "package_seq",
            strategy = "com.techacademy.logisticpackage.commons.generators.PackageIdGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = PackageIdGenerator.VALUE_PREFIX_PARAMETER, value = "PKG") })
    public String packageId;
    @Column(name = "type", nullable = false)
    public String type;
    @Column(name = "condition")
    public String condition;
    @Column(name = "createdBy", nullable = false)
    public String createdBy;
    @Column(name = "orderRefType")
    public String orderRefType;
    @Column(name = "orderRefId", nullable = false)
    public String orderRefId;
    @Column(name = "createdRefType", nullable = false)
    public String createdRefType;
    @Column(name = "createdRefId", nullable = false)
    public String createdRefId;
    @Column(name = "weightInGrams")
    public Integer weightInGrams;
    @Column(name = "heightInCm")
    public Integer heightInCm;
    @Column(name = "widthInCm")
    public Integer widthInCm;
    @Column(name = "depthInCm")
    public Integer depthInCm;
    @Column(name = "volumeInDm3")
    public Integer volumeInDm3;
    @OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<LpnEntity> lpn;
    @OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<LabelEntity> label;
    @OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<ItemEntity> items;
    @Column(name = "country")
    public String country;
    @Column(name = "commerce")
    public String commerce;
}
