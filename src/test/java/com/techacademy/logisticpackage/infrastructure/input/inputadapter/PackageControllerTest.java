package com.techacademy.logisticpackage.infrastructure.input.inputadapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techacademy.logisticpackage.application.port.PackageServicePort;
import com.techacademy.logisticpackage.commons.utils.HeadersUtils;
import com.techacademy.logisticpackage.commons.utils.PackageUtils;
import com.techacademy.logisticpackage.infrastructure.input.dto.*;
import com.techacademy.logisticpackage.infrastructure.input.dto.responses.PackagePostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(PackageController.class)
public class PackageControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PackageServicePort packageServicePort;

    @MockBean
    private PackageUtils packageUtils;

    @MockBean
    private HeadersUtils headersUtils;

    private PackageDto packageDto;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void beforeEach() {
        packageDto = new PackageDto();
        packageDto.setType("BOX");
        packageDto.setCondition("OK");
        packageDto.setCreatedBy("RLO");
        packageDto.setOrderRefType("RLO");
        packageDto.setOrderRefId("RLO00000004L9");
        packageDto.setCreatedRefType("RLO");
        packageDto.setCreatedRefId("RLO0000000009-TEST-REDIS");
        packageDto.setWeightInGrams(5000);
        packageDto.setHeightInCm(50);
        packageDto.setWidthInCm(50);
        packageDto.setDepthInCm(50);
        packageDto.setVolumeInDm3(125);
        // Lpn
        List<LpnDto> packageLpnsList = new ArrayList<>();
        LpnDto packageLpn = new LpnDto();
        packageLpn.setPackageLpnType("CHILEXPRESS");
        packageLpn.setPackageLpnValue("993989833980001");
        packageLpnsList.add(packageLpn);
        packageDto.setLpn(packageLpnsList);
        // Labels
        List<LabelDto> packageLabelList = new ArrayList<>();
        LabelDto packageLabel = new LabelDto();
        packageLabel.setPackageLabelType("THREE_PL");
        packageLabel.setPackageLabelValue("993432342480001");
        packageLabel.setPackageLabelFormat("PDF");
        packageLabelList.add(packageLabel);
        packageDto.setLabel(packageLabelList);
        // Items
        ItemDto item = new ItemDto();
        item.setPackageContentType("Fragile");
        item.setOrderItemId("4ef624a4-af49-4477-91b9-df7ed103a238");
        item.setLogisticSkuId("ca3bb03a-2974-457f-9fcc-6e4077e5d060");
        item.setInternalPackageId("InternalPackageId");
        item.setOfferingId("GENERICO_SODIMAC");
        item.setSerialNumber("4CE0460D0G");
        item.setCondition("OK");
        QuantityNumberDto quantityNumberDto = new QuantityNumberDto();
        quantityNumberDto.setQuantityNumber(3);
        item.setQuantity(quantityNumberDto);
        ItemDimensionsDto itemDimensionsDto = new ItemDimensionsDto();
        itemDimensionsDto.setLengthInCm(15);
        itemDimensionsDto.setWidthInCm(15);
        itemDimensionsDto.setHeightInCm(15);
        itemDimensionsDto.setWeightInKg(1);
        itemDimensionsDto.setVolumeInDm3(4);
        item.setItemDimensions(itemDimensionsDto);
        item.setItemSize(50);
        List<ItemDto> items = new ArrayList<>();
        items.add(item);
        packageDto.setItems(items);
    }

    @Test
    void testCreatePackage() throws Exception {
        // Given
        PackagePostResponse packagePostResponse = new PackagePostResponse("PKG0000000001");
        String content = objectMapper.writeValueAsString(packageDto);
        // When
        when(packageServicePort.createPackage((PackageDto) any())).thenReturn(packagePostResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/logistic-packages/")
                .header("x-Country", "CL")
                .header("x-Commerce", "Commerce")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        ResultActions result = mvc.perform(requestBuilder);
        //then

        result.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(packagePostResponse)));
    }

    @Test
    void testCreatePackageErrorNoItems() throws Exception {

        packageDto.getItems().removeAll(this.packageDto.getItems());
        String content = objectMapper.writeValueAsString(packageDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/logistic-packages/")
                .header("x-Country", "CL")
                .header("x-Commerce", "Commerce")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions result = mvc.perform(requestBuilder);

        result.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetById() throws Exception{
        packageDto.setPackageId("PK0000000001");
        packageDto.setCountry("CL");
        packageDto.setCommerce(("Commerce"));
        String expected = (new ObjectMapper()).writeValueAsString(packageDto);
        when(packageServicePort.getById((String) "PK0000000001")).thenReturn(packageDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/logistic-packages/{packageId}", "PK0000000001")
                .header("x-Country", "CL")
                .header("x-Commerce", "Commerce");
        ResultActions result = mvc.perform(requestBuilder);
        result.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(expected));
    }

    @Test
    void testGetByIdNotFound() throws Exception{
        when(packageServicePort.getById((String) any())).thenThrow(HttpServerErrorException.InternalServerError.class);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/logistic-packages/{packageId}", "PK0000000001")
                .header("x-Country", "CL")
                .header("x-Commerce", "Commerce");
        ResultActions result = mvc.perform(requestBuilder);
        result.andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}


