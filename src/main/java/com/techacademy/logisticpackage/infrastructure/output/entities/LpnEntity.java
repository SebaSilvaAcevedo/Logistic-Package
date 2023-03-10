package com.techacademy.logisticpackage.infrastructure.output.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "lpn")
public class LpnEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="lpnId", updatable = false, nullable = false)
    public String lpnId;
    @Column(name="packageLpnType")
    public String packageLpnType;
    @Column(name="packageLpnValue")
    public String packageLpnValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "package_id")
    public PackageEntity packageEntity;

}
