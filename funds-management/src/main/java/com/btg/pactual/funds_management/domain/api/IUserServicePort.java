package com.btg.pactual.funds_management.domain.api;

import com.btg.pactual.funds_management.domain.model.Subscription;
import com.btg.pactual.funds_management.domain.model.User;

import java.util.List;

public interface IUserServicePort {
    List<Subscription> getSubscriptionsByUserId(String userId);
    List<Subscription> getTransactionsByUserId(String userId);
    User getUser(String userId);

}
