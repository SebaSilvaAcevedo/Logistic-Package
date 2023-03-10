package com.techacademy.logisticpackage.infrastructure.output.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="itemId", updatable = false, nullable = false)
    public String itemId;
    @Column(name = "packageContentType")
    public String packageContentType;
    @Column(name = "orderItemId", nullable = false)
    public String orderItemId;
    @Column(name = "logisticSkuId", nullable = false)
    public String logisticSkuId;
    @Column(name = "internalPackageId")
    public String internalPackageId;
    @Column(name = "offeringId")
    public String offeringId;
    @Column(name = "serialNumber")
    public String serialNumber;
    @Column(name = "condition")
    public String condition;
    @Column(name = "quantityNumber", nullable = false)
    public Integer quantityNumber;
    @Column(name = "lengthInCm")
    public Integer lengthInCm;
    @Column(name = "widthInCm")
    public Integer widthInCm;
    @Column(name = "heightInCm")
    public Integer heightInCm;
    @Column(name = "WeightInKg")
    public Integer weightInKg;
    @Column(name = "volumeInDm3")
    public Integer volumeInDm3;
    @Column(name = "itemSize")
    public Integer itemSize;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "package_id")
    public PackageEntity packageEntity;
}
