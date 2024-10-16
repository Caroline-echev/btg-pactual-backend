package com.btg.pactual.funds_management.adapter.driving.http.controller;

import com.btg.pactual.funds_management.adapter.driving.http.dto.response.FundResponse;
import com.btg.pactual.funds_management.adapter.driving.http.mapper.IFundDtoMapper;
import com.btg.pactual.funds_management.domain.api.IFundServicePort;
import com.btg.pactual.funds_management.data.FundData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FundControllerTest {

    @Mock
    private IFundServicePort fundServicePort;

    @Mock
    private IFundDtoMapper fundDtoMapper;

    @InjectMocks
    private FundController fundController;

    @Test
    void getFunds_shouldReturnListOfFunds() {
        // Arrange
        List<FundResponse> expectedResponse = FundData.getFundsResponse();

        when(fundServicePort.getFunds(null, true, true)).thenReturn(FundData.getFunds());

        when(fundDtoMapper.mapToDtoResponse(FundData.getFundA())).thenReturn(expectedResponse.get(0));
        when(fundDtoMapper.mapToDtoResponse(FundData.getFundB())).thenReturn(expectedResponse.get(1));
        when(fundDtoMapper.mapToDtoResponse(FundData.getFundC())).thenReturn(expectedResponse.get(2));

        // Act
        ResponseEntity<List<FundResponse>> responseEntity = fundController.getFunds(null, true, true);

        // Assert
        assertEquals(expectedResponse, responseEntity.getBody());
    }


    @Test
    void getFunds_shouldReturnListOfFundsByCategory() {
        // Arrange
        List<FundResponse> expectedResponse = List.of(FundData.getFundsResponse().get(0)); // Fund A

        // Mocking the service layer
        String category = FundData.CATEGORY_1;
        when(fundServicePort.getFunds(category, true, true)).thenReturn(List.of(FundData.getFundA()));

        // Mocking the DTO mapper
        when(fundDtoMapper.mapToDtoResponse(FundData.getFundA())).thenReturn(expectedResponse.get(0));

        // Act
        ResponseEntity<List<FundResponse>> responseEntity = fundController.getFunds(category, true, true);

        // Assert
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
