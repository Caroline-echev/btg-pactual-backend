package com.btg.pactual.funds_management.adapter.driving.http.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.btg.pactual.funds_management.adapter.driving.http.util.DtoConstants.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnsubscribeRequest  {
    @NotBlank(message = USER_ID_BLANK_MESSAGE)
    private String userId;
    @NotBlank(message = FUND_ID_BLANK_MESSAGE)
    private String fundId;
    @NotNull(message = SMS_OR_EMAIL_NOTIFICATION)
    private boolean isSMS;

}