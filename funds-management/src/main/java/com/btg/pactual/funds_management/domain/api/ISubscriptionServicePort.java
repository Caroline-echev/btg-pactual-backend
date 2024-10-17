package com.btg.pactual.funds_management.domain.api;

import java.math.BigDecimal;
import java.util.List;

public interface ISubscriptionServicePort {

    void subscribeToFund(String userId, String fundId, Boolean isSMS, BigDecimal amount);
    void unsubscribeToFund(String userId, String fundId, Boolean isSMS);
}
