package com.btg.pactual.funds_management.adapter.driving.http.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.btg.pactual.funds_management.adapter.driving.http.util.DtoConstants.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriptionRequest {
    @NotBlank(message = USER_ID_BLANK_MESSAGE)
    private String userId;
    @NotBlank(message = FUND_ID_BLANK_MESSAGE)
    private String fundId;
    @NotNull(message = SMS_OR_EMAIL_NOTIFICATION)
    private Boolean isSMS;
    @NotNull(message = AMOUNT_NOT_NULL_MESSAGE)
    @DecimalMin(value = AMOUNT_MIN_VALUE, message = AMOUNT_POSITIVE_MESSAGE)
    private BigDecimal amount;
}
