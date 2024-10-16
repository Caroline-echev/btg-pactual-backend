package com.btg.pactual.funds_management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    private String id;
    private String userId;
    private String fundId;
    private String transactionType;
    private BigDecimal amount;
    private LocalDateTime dateTime;

}
