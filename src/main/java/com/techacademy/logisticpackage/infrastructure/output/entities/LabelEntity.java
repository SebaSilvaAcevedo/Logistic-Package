package com.techacademy.logisticpackage.infrastructure.output.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "label")
public class LabelEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="labelId", updatable = false, nullable = false)
    public String labelId;

    @Column(name="packageLabelType")
    public String packageLabelType;
    @Column(name="packageLabelValue" , columnDefinition="TEXT")
    public String packageLabelValue;
    @Column(name="packageLabelFormat")
    public String packageLabelFormat;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "package_id")
    public PackageEntity packageEntity;
}
