package com.btg.pactual.funds_management.adapter.driving.http.controller;

import com.btg.pactual.funds_management.adapter.driving.http.dto.response.FundResponse;
import com.btg.pactual.funds_management.adapter.driving.http.mapper.IFundDtoMapper;
import com.btg.pactual.funds_management.domain.api.IFundServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.btg.pactual.funds_management.adapter.driving.http.util.ApiConstants.*;

@RestController
@RequestMapping("/api/v1/funds/")
@RequiredArgsConstructor
public class FundController {
    private final IFundServicePort fundServicePort;
    private final IFundDtoMapper fundDtoMapper;

    @GetMapping
    public ResponseEntity<List<FundResponse>> getFunds(
            @RequestParam(required = false, name = CATEGORY_PARAM) String category,
            @RequestParam(defaultValue = DEFAULT_ORDER_BY_NAME, name = ORDER_BY_NAME_PARAM) boolean orderByName,
            @RequestParam(defaultValue = DEFAULT_IS_ASC, name = IS_ASC_PARAM) boolean isAsc) {

        List<FundResponse> fundResponses = fundServicePort.getFunds(category, orderByName, isAsc)
                .stream()
                .map(fundDtoMapper::mapToDtoResponse)
                .toList();

        return ResponseEntity.ok(fundResponses);
    }
}
