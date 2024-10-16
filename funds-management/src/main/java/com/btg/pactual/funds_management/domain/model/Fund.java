package com.btg.pactual.funds_management.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fund {
    private String id;
    private String name;
    private BigDecimal minimumAmount;
    private String category;
}