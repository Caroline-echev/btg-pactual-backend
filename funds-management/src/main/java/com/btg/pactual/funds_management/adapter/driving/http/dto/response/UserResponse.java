package com.btg.pactual.funds_management.adapter.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
    private BigDecimal initialBalance;
    private LocalDateTime dateOfBirth;
    private List<String> subscriptions;
}