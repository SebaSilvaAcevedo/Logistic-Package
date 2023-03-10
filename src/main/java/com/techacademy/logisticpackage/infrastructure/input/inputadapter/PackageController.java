package com.techacademy.logisticpackage.infrastructure.input.inputadapter;

import com.techacademy.logisticpackage.application.port.PackageServicePort;
import com.techacademy.logisticpackage.commons.exception.EnumRequestHeaderException;
import com.techacademy.logisticpackage.commons.utils.HeadersUtils;
import com.techacademy.logisticpackage.commons.utils.PackageUtils;
import com.techacademy.logisticpackage.infrastructure.input.dto.PackageDto;
import com.techacademy.logisticpackage.infrastructure.input.dto.responses.PackagePostResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/logistic-packages")
@Log4j2
public class PackageController {
    @Autowired
    private PackageServicePort inputPort;
    @Autowired
    private PackageUtils packageUtils;
    @Autowired
    private HeadersUtils headersUtils;
    @PostMapping("/")
    public ResponseEntity<PackagePostResponse> createPackage(
            @RequestBody @Valid PackageDto packageDto,
            @RequestHeader(required = true, name = "x-Country")  String xCountry,
            @RequestHeader(required = true, name = "x-Commerce") String xCommerce,
            @RequestHeader(required = false, name = "x-Environment") String xEnvironment
    ) throws EnumRequestHeaderException {
        try {
            headersUtils.validateCountryEnumHeader(xCountry);
            headersUtils.validateCommerceEnumHeader(xCommerce);
            packageDto.setCountry(xCountry);
            packageDto.setCommerce(xCommerce);
            log.info("Request Start, Operation: createPackage, Data: "+ packageUtils.toJson(packageDto));
            var postResponse = inputPort.createPackage(packageDto);
            ResponseEntity<PackagePostResponse> response = new ResponseEntity<>(postResponse, HttpStatus.CREATED);
            log.info("Request Finish, Operation: createPackage, Data: "+ packageUtils.toJson(response));
            return response;
        }
        catch (Exception e){
            log.error("{}, Error in POST service {}", e, e.getMessage());
            throw e;
        }
    }
    @GetMapping("/{packageId}")
    public ResponseEntity<PackageDto> getById(
            @PathVariable String packageId,
            @RequestHeader(required = true, name = "x-Country") String xCountry,
            @RequestHeader(required = true, name = "x-Commerce") String xCommerce,
            @RequestHeader(required = false, name = "x-Environment")  String xEnvironment
    )
    {
        try {
            log.info("Request Start, Operation: getById, Data:{\"packageId\":\"{}\"}", packageId);
            ResponseEntity<PackageDto> response = new ResponseEntity<>(inputPort.getById(packageId),HttpStatus.OK);
            log.info("Request Finish, Operation: getById, Data: {}", packageUtils.toJson(response));
            return response;
        }
        catch (Exception e){
            throw e;
        }
    }
}