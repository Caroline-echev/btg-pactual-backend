package com.btg.pactual.funds_management.domain.api;

import java.math.BigDecimal;

public interface ISubscriptionServicePort {

    void subscribeToFund(String userId, String fundId, boolean isSMS, BigDecimal amount);
}
