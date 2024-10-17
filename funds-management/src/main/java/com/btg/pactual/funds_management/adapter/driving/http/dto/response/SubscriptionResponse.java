package com.btg.pactual.funds_management.adapter.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubscriptionResponse {
    private String userId;
    private String fundId;
    private String fundName;
    private BigDecimal subscriptionAmount;
    private String subscriptionType;
    private LocalDateTime dateTime;

}
