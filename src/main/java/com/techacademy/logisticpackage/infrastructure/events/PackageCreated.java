package com.techacademy.logisticpackage.infrastructure.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PackageCreated {
    public String packageId;
}
