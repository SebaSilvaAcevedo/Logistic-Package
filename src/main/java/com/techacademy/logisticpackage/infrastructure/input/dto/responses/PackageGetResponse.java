package com.techacademy.logisticpackage.infrastructure.input.dto.responses;

import com.techacademy.logisticpackage.infrastructure.input.dto.ItemDto;
import com.techacademy.logisticpackage.infrastructure.input.dto.LabelDto;
import com.techacademy.logisticpackage.infrastructure.input.dto.LpnDto;

import java.util.List;

public class PackageGetResponse {
    public String packageId;
    public String type;
    public String condition;
    public String createdBy;
    public String orderRefType;
    public String orderRefId;
    public String createdRefType;
    public String createdRefId;
    public Integer weightInGrams;
    public Integer heightInCm;
    public Integer widthInCm;
    public Integer depthInCm;
    public Integer volumeInDm3;
    public List<LpnDto> lpn;
    public List<LabelDto> label;
    public List<ItemDto> items;
}
