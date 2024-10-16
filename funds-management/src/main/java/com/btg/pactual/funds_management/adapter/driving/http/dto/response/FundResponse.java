package com.btg.pactual.funds_management.adapter.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FundResponse {
    private String id;
    private String name;
    private BigDecimal minimumAmount;
    private String category;
}
